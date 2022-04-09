import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
    private File data;

    public Server() throws IOException {
        data = new File("data.xls");
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

    private Workbook getWorkBookWithAccounts() {
        try {
            HSSFWorkbook wb;
            if (!data.exists()) {
                data.createNewFile();
                wb = new HSSFWorkbook();
                wb.createSheet();
            } else {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(data));
                wb = new HSSFWorkbook(fs);
            }
            return wb;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean registerClient (String login, String password) {
        try {
            HSSFWorkbook wb = (HSSFWorkbook) getWorkBookWithAccounts();
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIt = sheet.rowIterator();
            int cnt = 0;
            while (rowIt.hasNext()) {
                HSSFRow row = (HSSFRow) rowIt.next();
                Cell cell = row.getCell(1);
                if (login.equals(cell.toString())) {
                    return false;
                }
                cnt++;
            }
            sheet.createRow(cnt);
            HSSFRow row = sheet.getRow(cnt);
            row.createCell(0);
            row.createCell(1);
            row.createCell(2);
            row.createCell(3);
            Cell c = row.getCell(0);
            c.setCellValue("-");
            c = row.getCell(1);
            c.setCellValue(login);
            c = row.getCell(2);
            c.setCellValue(password);
            c = row.getCell(3);
            c.setCellValue("offline");
            wb.write(data);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean loginClient (String login, String password, String ip) {
        System.out.println(login + ' ' + password);
        try {
           HSSFWorkbook wb = (HSSFWorkbook)getWorkBookWithAccounts();
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIt = sheet.rowIterator();
            while (rowIt.hasNext()) {
                HSSFRow row = (HSSFRow) rowIt.next();
                Cell cellLogin = row.getCell(1);
                Cell cellPassword = row.getCell(2);
                if (login.equals(cellLogin.toString()) && password.equals(cellPassword.toString())) {
                    Cell c = row.getCell(3);
                    c.setCellValue("online");
                    c = row.getCell(0);
                    c.setCellValue(ip);
                    wb.write(data);
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void logout (String login) {
        try {
            HSSFWorkbook wb = (HSSFWorkbook)getWorkBookWithAccounts();
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIt = sheet.rowIterator();
            while (rowIt.hasNext()) {
                HSSFRow row = (HSSFRow) rowIt.next();
                if (login.equals(row.getCell(0).toString())) {
                    Cell c = row.getCell(3);
                    c.setCellValue("offline");
                    wb.write(data);
                    return;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getOnlineList () {
        ArrayList<String> list = new ArrayList<>();
        HSSFWorkbook wb = (HSSFWorkbook)getWorkBookWithAccounts();
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator rowIt = sheet.rowIterator();
        while (rowIt.hasNext()) {
            HSSFRow row = (HSSFRow) rowIt.next();
            if ("online".equals(row.getCell(3).toString())) {
                list.add(row.getCell(1).toString());
            }
        }
        return list;
    }

    public void ClientSession(Socket client) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();
            if (line == null)
                return;
            int firstIndexWhitespace = line.indexOf(' ', 11);

            if (firstIndexWhitespace == -1)
                firstIndexWhitespace = line.length();

            String command = line.substring(11, firstIndexWhitespace);
            String login = null;
            if (command.equals("register") || command.equals("login")) {
                // "command := register login := lonsfd password := sdlfkjsdf"
                int secondIndexWhitespace = line.indexOf(' ', firstIndexWhitespace + 10);
                if (secondIndexWhitespace == -1)
                    secondIndexWhitespace = line.length();
                login = line.substring(firstIndexWhitespace + 10, secondIndexWhitespace);
                String password = line.substring(secondIndexWhitespace + 13);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write(String.valueOf(command.equals("register") ? registerClient(login, password) : loginClient(login, password)));
                writer.newLine();
                writer.flush();
            }
            if (command.equals("onlineList")) {
                // "command := onlineList"
                ArrayList<String> result = getOnlineList();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write(result.size());
                writer.newLine();
                for (String LOGIN : result) {
                    writer.write(LOGIN);
                    writer.newLine();
                }
                writer.flush();
            }
            if (command.equals("logout")) {
                // "command := logout login := lonsfd"
                int secondIndexWhitespace = line.indexOf(' ', firstIndexWhitespace + 10);
                if (secondIndexWhitespace == -1)
                    secondIndexWhitespace = line.length();
                login = line.substring(firstIndexWhitespace + 10, secondIndexWhitespace);
                System.out.println("logout " + login);
                logout(login);
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}