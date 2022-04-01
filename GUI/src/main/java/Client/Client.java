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
        bufferedWriter.write("hello");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        clientSocket.close();
        outputStreamWriter.close();
        bufferedWriter.close();
    }
}
