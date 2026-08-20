package com.lld.BehaviouralDesignPatterns.ObserverPattern.Problem;
class DisplayDevice {
    public void showTemp(float temp) {
        System.out.println("Temperature is: " + temp);
    }
}

class WeatherStation {
    private float temperature;
    private DisplayDevice displayDevice;

    public void setDisplayDevice(DisplayDevice ds) {
        displayDevice = ds;
    }

    public void setTemperature(float temp) {
        temperature = temp;
        notifyDevice();
    }

    public void notifyDevice() {
        displayDevice.showTemp(temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        DisplayDevice displayDevice = new DisplayDevice();
        // bad design: tight coupling b/w station and device
        weatherStation.setDisplayDevice(displayDevice);
        weatherStation.setTemperature(25.0f);
    }
}
