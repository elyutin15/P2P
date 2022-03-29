package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static void ClientSession(Socket clientSock) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
            clientSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args){
        try {
            server = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket client = server.accept();
                Thread sessionThread = new Thread(() -> {
                    ClientSession(client);
                });
                sessionThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static ServerSocket server;
    private static final int SERVER_PORT = 10001;

}