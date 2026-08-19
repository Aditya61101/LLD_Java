package com.lld.CreationDesignPatterns.Factory.Problem;

public class TransportService {
    public static void main(String[] args) {
        // directly create objects
        Transport car = new Car();
        car.deliver();
        // tight coupling of client(Transport service) with object creation
        Transport bike = new Bike();
        bike.deliver();
    }
}
