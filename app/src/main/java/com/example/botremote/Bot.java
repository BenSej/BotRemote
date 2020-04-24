import com.pi4j.io.gpio.*;

class Bot {
    private boolean stopped = true;
    static GpioController eOne = GpioFactory.getInstance();
    static GpioController eTwo = GpioFactory.getInstance();
    static GpioController iOne = GpioFactory.getInstance();
    static GpioController iTwo = GpioFactory.getInstance();
    static GpioController iThree = GpioFactory.getInstance();
    static GpioController iFour = GpioFactory.getInstance();
    static GpioPinDigitalOutput enableOne = eOne.provisionDigitalOutputPin(RaspiPin.GPIO_04, "enableOne", PinState.LOW);
    static GpioPinDigitalOutput enableTwo = eTWo.provisionDigitalOutputPin(RaspiPin.GPIO_18, "enableTwo", PinState.LOW);
    static GpioPinDigitalOutput inOne = iOne.provisionDigitalOutputPin(RaspiPin.GPIO_02, "inOne", PinState.LOW);
    static GpioPinDigitalOutput inTwo = iTwo.provisionDigitalOutputPin(RaspiPin.GPIO_03, "inTwo", PinState.LOW);
    static GpioPinDigitalOutput inThree = iThree.provisionDigitalOutputPin(RaspiPin.GPIO_14, "inThree", PinState.LOW);
    static GpioPinDigitalOutput inFour = iFour.provisionDigitalOutputPin(RaspiPin.GPIO_15, "inFour", PinState.LOW);

    static void moveForward() {
        if (stopped) {
            stopped = false;
            //configure first motor current for forward movement
            inOne.high();
            //configure second motor current for forward movement
            inThree.high();
            //enable both motors
            enableOne.high();
            enableTwo.high();
        }
    }
    static void stop() {
        //diable all current flow and disable motors
        inOne.low();
        inTwo.low();
        inThree.low();
        inFour.low();
        enableOne.low();
        enableTwo.low();
        stopped = true;
    }
    static void moveRight() {
        if (stopped) {
            stopped = false;
            //configure first motor current for forward movement
            inOne.high();
            //configure second motor current for backward movement
            inFour.high();
            //enable both motors
            enableOne.high();
            enableTwo.high();
        }
    }
    static void moveLeft() {
        if (stopped) {
            stopped = false;
            //configure first motor for backward movement
            inTwo.high();
            //configure second motor for forward movement
            inThree.high();
            //enable both motors
            enableOne.high();
            enableTwo.high();
        }
    }
    static void moveBackward() {
        if (stopped) {
            //configure first motor current for backward movement
            inTwo.high();
            //configure second motor current for backward movement
            inFour.high();
            //enable both motors
            enableOne.high();
            enableTwo.high();
        }
    }
}