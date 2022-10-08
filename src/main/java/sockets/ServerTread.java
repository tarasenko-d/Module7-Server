package sockets;

import server.ChatServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerTread extends Thread {

    private ChatServer server;
    private Socket clientSocket;
    private Scanner in;
    private PrintWriter out;

    public ServerTread(Socket clientSocket, ChatServer server) {
        this.clientSocket = clientSocket;
        this.server=server;
        try {
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            server.sendAll("New Socket");
            if (in.hasNext()){
                String clientMsg = in.nextLine();
                System.out.println(clientMsg);
                server.sendAll(clientMsg);
                System.out.println("Send all message");
            }
        }
    }

    public void sendMessage(String message){
        out.write(message);
        System.out.println("write msg ");
        out.flush();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
