package com.example.botremote;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Bot {

    public static void main(String[] args) throws InterruptedException {
        //create gpio controller 1
        final GpioController gpio1 = GpioFactory.getInstance();
        //create gpio controller 2
        final GpioController gpio2 = GpioFactory.getInstance();
        //provision gpio pin #01 as an output pin and turn off
        final GpioPinDigitalOutput pin1 = gpio1.ProvisionDigitalOutputPin(RaspiPin.GPIO_01, "Right", PinState.LOW);
        //provision gpio pin#02 as an output pin and turn off
        final GpioPinDigitalOutput pin2 = gpio2.ProvisionDigitalOutputPin(RaspiPin.GPIO_02, "Left", PinState.LOW);
    }

    public static void moveForward() {
        pin1.HIGH();
        pin2.HIGH();
    }
    public static void stop() {
        pin1.LOW();
        pin2.LOW();
    }
}