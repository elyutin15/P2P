import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Iterator;

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
            ClientSession(client);
            /*Thread sessionThread = new Thread(() -> {
                ClientSession(client);
            });
            sessionThread.start();*/
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
                    wb.close();
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
            wb.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean LoginClient (String login, String password) {
        try {
            File data = new File("data.xls");
            HSSFWorkbook wb;
            if (!data.exists()) {
                data.createNewFile();
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
    public void ClientSession(Socket client) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String clientMessage = bufferedReader.readLine();
        if(clientMessage.startsWith("command := register")) {
            String finalClientMessage = clientMessage.substring(20);
            System.out.println(finalClientMessage);
            RegisterClient("nicedick", "pudgeisfavor");
            RegisterClient("nice62636132dick", "pudgeisfavo1234r");
            RegisterClient("nicedic12344k", "pudgeis1234favor");
            RegisterClient("1235nicedick", "pudgeis1616favor");
        }
        System.out.println(LoginClient("h", "2"));
        System.out.println(LoginClient("nicedick", "2"));
        System.out.println(LoginClient("nicedick", "pudgeisfavor"));
    }




}