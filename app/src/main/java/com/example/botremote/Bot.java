import com.pi4j.io.gpio.*;
import java.net.*;
import java.io.*;

class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    private static void start() throws IOException {
        serverSocket = new ServerSocket(6197);
        System.out.println("Server listening on port 6197");
        clientSocket = serverSocket.accept();
        System.out.println("Connected");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;
        try {
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
            System.out.println("client disconnected");
            stop();
            start();
        } catch (IOException e) {
            System.out.println("disconnected");
            stop();
            start();
        }
    }
    private static void stop() throws IOException {
        serverSocket.close();
        clientSocket.close();
    }
    public static void main(String[] args) throws IOException {
        start();
    }
}

class Bot {
    private static GpioController eOne = GpioFactory.getInstance();
    private static GpioController eTwo = GpioFactory.getInstance();
    private static GpioController iOne = GpioFactory.getInstance();
    private static GpioController iTwo = GpioFactory.getInstance();
    private static GpioController iThree = GpioFactory.getInstance();
    private static GpioController iFour = GpioFactory.getInstance();
    private static GpioPinDigitalOutput enableOne = eOne.provisionDigitalOutputPin(RaspiPin.GPIO_06, "", PinState.HIGH);
    private static GpioPinDigitalOutput enableTwo = eTwo.provisionDigitalOutputPin(RaspiPin.GPIO_03, "", PinState.HIGH);
    private static GpioPinDigitalOutput inOne = iOne.provisionDigitalOutputPin(RaspiPin.GPIO_04, "", PinState.LOW);
    private static GpioPinDigitalOutput inTwo = iTwo.provisionDigitalOutputPin(RaspiPin.GPIO_05, "", PinState.LOW);
    private static GpioPinDigitalOutput inThree = iThree.provisionDigitalOutputPin(RaspiPin.GPIO_02, "", PinState.LOW);
    private static GpioPinDigitalOutput inFour = iFour.provisionDigitalOutputPin(RaspiPin.GPIO_00, "", PinState.LOW);

    static void moveForward () {
        inTwo.low();
        inFour.low();
        inOne.high();
        inThree.high();
    }
    static void stop () {
        inOne.low();
        inTwo.low();
        inThree.low();
        inFour.low();
    }
    static void moveRight () {
        inTwo.low();
        inThree.low();
        inOne.high();
        inFour.high();
    }
    static void moveLeft () {
        inOne.low();
        inFour.low();
        inTwo.high();
        inThree.high();
    }
    static void moveBackward () {
        inOne.low();
        inThree.low();
        inTwo.high();
        inFour.high();
    }
}