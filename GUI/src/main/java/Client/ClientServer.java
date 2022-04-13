package Client;

import java.io.*;
import java.net.Socket;

public class ClientServer {
    private Socket clientSocket;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    public ClientServer() throws IOException {
        clientSocket = new Socket("localhost",10001);
        outputStreamWriter= new OutputStreamWriter(clientSocket.getOutputStream());
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void sendMessage(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public String getResponse() throws IOException {
        return bufferedReader.readLine();
    }

    public void close() throws IOException {
        clientSocket.close();
        outputStreamWriter.close();
        bufferedWriter.close();
        inputStreamReader.close();
        bufferedReader.close();
    }
}
