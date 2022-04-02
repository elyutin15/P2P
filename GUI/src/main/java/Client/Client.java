package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;
    public Client() throws IOException {
        clientSocket = new Socket("localhost",10001);
        outputStreamWriter= new OutputStreamWriter(clientSocket.getOutputStream());
        bufferedWriter = new BufferedWriter(outputStreamWriter);
    }

    public void sendMessage(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void close() throws IOException {
        clientSocket.close();
        outputStreamWriter.close();
        bufferedWriter.close();
    }
}
