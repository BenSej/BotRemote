import com.pi4j.io.gpio.*;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

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
                    case "playRecording":
                        System.out.println("Playing Recording");
                        Command.enact(commands);
                        break;
                    case "startRecording":
                        System.out.println("Recording Started");
                        record();
                        break;
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

    private void record() {
        serverSocket = new ServerSocket(6197);
        //System.out.println("Server listening on port 6197");
        clientSocket = serverSocket.accept();
        //System.out.println("Connected");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;
        List<String> recording = new ArrayList<String>();
        int count = 0;
        try {
            while ((message = in.readLine()) != null || count < 10) {
                switch (message) {
                    case "endRecording":
                        recording.add("endRecording");
                        System.out.println("Recording Ended");
                        start();
                        break;
                    case "moveForward":
                        recording.add("moveForward");
                        System.out.println("Bot Moving forward Recorded");
                        count++;
                        break;
                    case "moveBackward":
                        recording.add("moveBackward");
                        System.out.println("Bot Moving Backwards");
                        count++;
                        break;
                    case "moveRight":
                        recording.add("moveRight");
                        System.out.println("Bot Moving Right Recorded");
                        count++;
                        break;
                    case "moveLeft":
                        recording.add("moveLeft");
                        System.out.println("Bot Moving Left Recorded");
                        count++;
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Recording Ended");
        } catch (IOException e) {
            System.out.println("Recording Error: Bot Disconnected");
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
        inOne.low();
        inThree.low();
        inTwo.high();
        inFour.high();
    }
    static void stop () {
        inOne.low();
        inTwo.low();
        inThree.low();
        inFour.low();
    }
    static void moveRight () {
        inThree.low();
        inTwo.low();
        inOne.low();
        inFour.high();
    }
    static void moveLeft () {
        inOne.low();
        inThree.low();
        inFour.low();
        inTwo.high();
    }
    static void moveBackward () {
        inTwo.low();
        inFour.low();
        inOne.high();
        inThree.high();
    }
}
public class Command {
    private static List<String> commands = new ArrayList<String>();

    public Programmed(List<String> input) {
        commands = input;
    }

    public static void enact(List<String> input) {
        for (int counter = 0; counter < 10; counter++) {
            if (input.get(counter).equals("moveForward")) {
                System.out.println("Bot Moving Forward from Recording");
                Bot.moveForward();
                wait(2000);
                Bot.stop();
            } else if (input.get(counter).equals("moveBackward")) {
                System.out.println("Bot Moving Backward from Recording");
                Bot.moveBackward();
                wait(2000);
                Bot.stop();
            } else if (input.get(counter).equals("moveLeft")) {
                System.out.println("Bot Moving Left from Recording");
                Bot.moveLeft();
                wait(2000);
                Bot.stop();
            } else if (input.get(counter).equals("moveRight")) {
                System.out.println("Bot Moving Right from Recording");
                Bot.moveRight();
                wait(2000);
                Bot.stop();
            } else {
                System.out.println("Bot Stopped from Recording");
                Bot.stop();
                return
            }
        }
        System.out.println("End of Recording")
        Bot.stop();
        return;
    }
}
