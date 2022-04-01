package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private final int SERVER_PORT = 10001;

    public Server() throws IOException {
        server = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket client = server.accept();
            Thread sessionThread = new Thread(() -> {
                ClientSession(client);
            });
            sessionThread.start();
        }
    }

    public void ClientSession(Socket client) {
        System.out.println("---\nSuccess connection\n---");

    }




}