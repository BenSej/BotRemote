package com.example.botremote;
import java.net.*;
import java.io.*;
class Client {
    public static Socket clientSocket = new Socket();
    private static PrintWriter out;

    static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }
    static void sendMessage(String message) throws IOException {
        if (out != null) {
            out.println(message);
        }
    }
    static void stopConnection() throws IOException {
        clientSocket.close();
    }
}
