package com.example.botremote;

import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean receiving;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("connected");
        receiving = true;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
        String message = in.readLine();
        while (receiving) {
            if (message.equals("moveForward")) {
                Bot.moveForward();
            }
            if (message.equals("stop")) {
                Bot.stop();
            } else {
                out.println("bad message");
                return;
            }
        }
    }
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        receiving = false;
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(0101);
    }
}