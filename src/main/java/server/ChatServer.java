package server;

import sockets.ServerTread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private List<ServerTread> treadList = new LinkedList<>();

    public ChatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
              clientSocket = serverSocket.accept();
              ServerTread tread = new ServerTread(clientSocket,this);
              treadList.add(tread);
              tread.start();
              System.out.println("Add new");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                serverSocket.close();
                System.out.println("All closed");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendAll(String message){
        for (ServerTread serverTread : treadList) {
            serverTread.sendMessage(message);
        }
    }
}
