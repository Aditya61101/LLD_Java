package com.lld.BehaviouralDesignPatterns.ObserverPattern.Solution;

import java.util.ArrayList;
import java.util.List;

// observer interface
interface Observer {
    void update(float temp);
}

class MobileDevice implements Observer {
    @Override
    public void update(float temp) {
        System.out.println("Mobile device: Temperature is: " + temp);
    }
}

class LaptopDevice implements Observer {
    @Override
    public void update(float temp) {
        System.out.println("Laptop device: Temperature is: " + temp);
    }
}

class WatchDevice implements Observer {
    @Override
    public void update(float temp) {
        System.out.println("Watch device: Temperature is: " + temp);
    }
}

// subject interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private final List<Observer> observers;
    private float temperature;

    WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notifying observers...");
        for(Observer o: observers) {
            // runtime polymorphism
            o.update(temperature);
        }
    }

    public void setTemperature(float temp) {
        System.out.println("Temperature changed!!");
        temperature = temp;
        notifyObservers();
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();

        ws.registerObserver(new LaptopDevice());
        ws.registerObserver(new MobileDevice());
        ws.registerObserver(new WatchDevice());

        ws.setTemperature(25.0f);
    }
}
