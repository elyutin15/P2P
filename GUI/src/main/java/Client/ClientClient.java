package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientClient implements Runnable {
    private Socket clientSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;

    public ClientClient(String friendIp) throws IOException {
        clientSocket = new Socket(InetAddress.getByName(friendIp), 4040, InetAddress.getByName(InetAddress.getLocalHost().getHostAddress()), 8080);
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
        bufferedWriter = new BufferedWriter(outputStreamWriter);
    }

    public void sendMessage(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public void closeSocket() throws IOException {
        inputStreamReader.close();
        bufferedReader.close();
        outputStreamWriter.close();
        bufferedWriter.close();
        clientSocket.close();
    }

    @Override
    public void run() {
        do {
            String messageFromFriend = null;
            try {
                messageFromFriend = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(messageFromFriend);
        }while (true);
    }
}
