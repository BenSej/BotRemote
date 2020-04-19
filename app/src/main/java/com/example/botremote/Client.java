package com.example.botremote;
import java.net.*;
import java.io.*;
class Client {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    static void sendMessage(String message) throws IOException {
        out.println(message);
        in.readLine();
    }
    static void stopConnection() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
    }
}
