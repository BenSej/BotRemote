import com.pi4j.io.gpio.*;
import java.net.*;
import java.io.*;

class Server {
    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6197);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connected");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;
        while ((message = in.readLine()) != null) {
            switch (message) {
                case "moveForward":
                    Bot.moveForward();
                    System.out.println("Bot Moving Forwards");
                    break;
                case "moveBackward":
                    Bot.moveBackward();
                    System.out.println("Bot Moving Backwards");
                    break;
                case "moveRight":
                    Bot.moveRight();
                    System.out.println("Bot Moving Right");
                    break;
                case "moveLeft":
                    Bot.moveLeft();
                    System.out.println("Bot Moving Left");
                    break;
                case "stop":
                    Bot.stop();
                    System.out.println("Bot Stopped");
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}

class Bot {
    static private boolean stopped = true;
    private static GpioController eOne = GpioFactory.getInstance();
    private static GpioController eTwo = GpioFactory.getInstance();
    private static GpioController iOne = GpioFactory.getInstance();
    private static GpioController iTwo = GpioFactory.getInstance();
    private static GpioController iThree = GpioFactory.getInstance();
    private static GpioController iFour = GpioFactory.getInstance();
    private static GpioPinDigitalOutput enableOne = eOne.provisionDigitalOutputPin(RaspiPin.GPIO_06, "", PinState.LOW);
    private static GpioPinDigitalOutput enableTwo = eTwo.provisionDigitalOutputPin(RaspiPin.GPIO_03, "", PinState.LOW);
    private static GpioPinDigitalOutput inOne = iOne.provisionDigitalOutputPin(RaspiPin.GPIO_04, "", PinState.LOW);
    private static GpioPinDigitalOutput inTwo = iTwo.provisionDigitalOutputPin(RaspiPin.GPIO_05, "", PinState.LOW);
    private static GpioPinDigitalOutput inThree = iThree.provisionDigitalOutputPin(RaspiPin.GPIO_02, "", PinState.LOW);
    private static GpioPinDigitalOutput inFour = iFour.provisionDigitalOutputPin(RaspiPin.GPIO_00, "", PinState.LOW);

    static void moveForward () {
        if (stopped) {
            stopped = false;
            inOne.high();
            inThree.high();
            enableOne.high();
            enableTwo.high();
        }
    }
    static void stop () {
        inOne.low();
        inTwo.low();
        inThree.low();
        inFour.low();
        enableOne.low();
        enableTwo.low();
        stopped = true;
    }
    static void moveRight () {
        if (stopped) {
            stopped = false;
            inOne.high();
            inFour.high();
            enableOne.high();
            enableTwo.high();
        }
    }
    static void moveLeft () {
        if (stopped) {
            stopped = false;
            inTwo.high();
            inThree.high();
            enableOne.high();
            enableTwo.high();
        }
    }
    static void moveBackward () {
        if (stopped) {
            inTwo.high();
            inFour.high();
            enableOne.high();
            enableTwo.high();
        }
    }
}