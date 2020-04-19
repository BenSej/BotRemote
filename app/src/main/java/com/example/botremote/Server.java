package com.example.botremote;

import java.net.*;
import java.io.*;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static boolean receiving;

    private void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("connected");
        receiving = true;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
        String message = in.readLine();
        while (receiving) switch (message) {
            case "moveForward":
                Bot.moveForward();
                break;
            case "stop":
                Bot.stop();
                break;
            case "moveRight":
                Bot.moveRight();
                break;
            case "moveLeft":
                Bot.moveLeft();
                break;
            case "moveBackward":
                Bot.moveBackward();
                break;
            default:
                out.println("bad message");
                return;
        }
    }
    static void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        receiving = false;
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6000);
    }
}