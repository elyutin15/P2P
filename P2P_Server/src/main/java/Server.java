import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

public class Server {
    private ServerSocket server;
    private final int SERVER_PORT = 10001;

    public Server() throws IOException {
        server = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket client = server.accept();
            System.out.println("---\nSuccess connection\n---");
            Thread sessionThread = new Thread(() -> {
                ClientSession(client);
            });
            sessionThread.start();
        }
    }

    public boolean RegisterClient (String login, String password) {
        try {
            File data = new File("data.xls");
            HSSFWorkbook wb;
            if (!data.exists()) {
                data.createNewFile();
                wb = new HSSFWorkbook();
                wb.createSheet();
            }
            else {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(data));
                wb = new HSSFWorkbook(fs);
            }
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIt = sheet.rowIterator();
            int cnt = 0;
            while (rowIt.hasNext()) {
                HSSFRow row = (HSSFRow) rowIt.next();
                Iterator cellIt = row.cellIterator();
                if (login.equals(cellIt.next().toString())) {
                    return false;
                }
                cnt++;
            }
            sheet.createRow(cnt);
            HSSFRow row = sheet.getRow(cnt);
            row.createCell(0);
            row.createCell(1);
            Cell c = row.getCell(0);
            c.setCellValue(login);
            c = row.getCell(1);
            c.setCellValue(password);
            wb.write(data);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean LoginClient (String login, String password) {
        try {
            File data = new File("data.xls");
            HSSFWorkbook wb;
            if (!data.exists()) {
                wb = new HSSFWorkbook();
                wb.createSheet();
            } else {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(data));
                wb = new HSSFWorkbook(fs);
            }
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIt = sheet.rowIterator();
            while (rowIt.hasNext()) {
                HSSFRow row = (HSSFRow) rowIt.next();
                Iterator cellIt = row.cellIterator();
                if (login.equals(cellIt.next().toString()) && password.equals(cellIt.next().toString())) {
                    wb.close();
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void ClientSession(Socket client) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();
            if (line == null)
                return;
            int firstIndexWhitespace = line.indexOf(' ', 11);
            String command = line.substring(11, firstIndexWhitespace);
            if (command.equals("register") || command.equals("login")) {
                // "command := register login := lonsfd password := sdlfkjsdf
                int secondIndexWhitespace = line.indexOf(' ', firstIndexWhitespace + 10);
                String login = line.substring(firstIndexWhitespace + 10, secondIndexWhitespace);
                String password = line.substring(secondIndexWhitespace + 13);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write(String.valueOf(command.equals("register") ? RegisterClient(login, password) : LoginClient(login, password)));
                writer.newLine();
                writer.flush();
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}