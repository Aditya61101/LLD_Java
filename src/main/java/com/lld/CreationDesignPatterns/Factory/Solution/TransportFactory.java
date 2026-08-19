package com.lld.CreationDesignPatterns.Factory.Solution;

public class TransportFactory {
    public static Transport getTransport(String transport) {
        return switch (transport.toLowerCase()) {
            case "bus" -> new Bus();
            case "bike" -> new Bike();
            case "car" -> new Car();
            default -> throw new IllegalArgumentException("Invalid transport type");
        };
    }
}
