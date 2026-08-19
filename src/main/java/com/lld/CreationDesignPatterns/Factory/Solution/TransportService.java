package com.lld.CreationDesignPatterns.Factory.Solution;

public class TransportService {
    public static void main(String[] args) {
        Transport car = TransportFactory.getTransport("car");
        car.deliver();

        Transport bus = TransportFactory.getTransport("bus");
        bus.deliver();

        Transport bike = TransportFactory.getTransport("bike");
        bike.deliver();
    }
}
