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
        System.out.println("Server Started!");
        Socket socket = serverSocket.accept();
        System.out.println("Client Connected!");
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        String output = JsonHandler.entry(din);
        dout.writeUTF(output);

        // Conditional if we want to close server/client communication
        din.close();
        socket.close();
        System.out.println("Client Disconnected!");
        serverSocket.close();
        System.out.println("Server Socket Closing!");
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
