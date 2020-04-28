import java.net.*;
import java.io.*;

class Server {
    public static Server server = new Server();
    public static ServerSocket serverSocket;
    public static Socket clientSocket;

    public static void start() throws IOException {
        serverSocket = new ServerSocket(6197);
        System.out.println("Server listening on port 6197");
        clientSocket = serverSocket.accept();
        System.out.println("Connected");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String message;
        try {
	        while ((message = in.readLine()) != null) {
	            switch (message) {
	                case "moveForward":
	                    System.out.println("Bot Moving Forwards");
	                    break;
	                case "moveBackward":
	                    System.out.println("Bot Moving Backwards");
	                    break;
	                case "moveRight":
	                    System.out.println("Bot Moving Right");
	                    break;
	                case "moveLeft":
	                    System.out.println("Bot Moving Left");
	                    break;
	                case "stop":
	                    System.out.println("Bot Stopped");
	                    break;
	                default:
	                    break;
	            }
	        }
	        System.out.println("client disconnected");
	        stop();
	        start();
	    } catch (IOException e) {
	    	System.out.println("disconnected");
	    	stop();
	    	start();
	    }
    }
    public static void stop() throws IOException {
    	serverSocket.close();
    	clientSocket.close();
    }
    public static void main(String[] args) throws IOException {
    	start();
    }
}