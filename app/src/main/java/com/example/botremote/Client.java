package com.example.botremote;
import java.net.*;
import java.io.*;
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public String sendMessage(String message) throws IOException {
        out.println(message);
        return "Returned: " + in.readLine();
    }
    public void stopConnection() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
    }
}
