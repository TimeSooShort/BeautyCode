package DesignPattern.JUCdp.ThreadPerMsg.Sample.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {

    private final int port;

    public MiniServer(int port) {
        this.port = port;
    }

    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + serverSocket);
        try {
            while (true) {
                System.out.println("Accepting....");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket);
                try {
                    Service.service(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
