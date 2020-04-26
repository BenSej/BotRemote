package com.example.botremote;

import java.net.*;
import java.io.*;

class Client {
    static Socket clientSocket = new Socket();
    private static PrintWriter out;

    static void startConnection(String ip) throws IOException {
        clientSocket = new Socket(ip, 6197);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }
    static void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}
