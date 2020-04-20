import java.net.*;
import java.io.*;
import com.pi4j.*;

class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader in;

    private void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("Connected");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = "";
        while ((message = in.readLine()) != null) {
            switch (message) {
                case "moveForward":
                    System.out.println("Bot Moving Forwards");
                    Bot.moveForward();
                    break;
                case "moveBackward":
                    System.out.println("Bot Moving Backwards");
                    Bot.moveBackward();
                    break;
                case "moveRight":
                    System.out.println("Bot Moving Right");
                    Bot.moveRight();
                    break;
                case "moveLeft":
                    System.out.println("Bot Moving Left");
                    Bot.moveLeft();
                    break;
                case "stop":
                    System.out.println("Bot Stopped");
                    Bot.stop();
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6197);
    }
}

class Bot {
    
    static GpioController gpio2 = GpioFactory.getInstance();
    static GpioPinDigitalOutput pin2 = gpio2.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Right", PinState.LOW);
    static GpioController gpio1 = GpioFactory.getInstance();
    static GpioPinDigitalOutput pin1 = gpio1.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Left", PinState.LOW);
    
    static void moveForward() {
    }
    static void stop() {
    }
    static void moveRight() {
    }
    static void moveLeft() {
    }
    static void moveBackward() {
    }
}