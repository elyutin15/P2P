package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PermanentServerListener {
    public PermanentServerListener() throws IOException {
        Socket socket = new Socket("localhost", 10001);
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        do {
            System.out.println(bufferedReader.readLine());
        } while (true);
    }
}
