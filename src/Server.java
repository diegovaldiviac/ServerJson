import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(this.port);
        Socket socket = serverSocket.accept();
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        String output = JsonHandler.entry(din);
        dout.writeUTF(output);
        din.close();
        socket.close();
        serverSocket.close();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
