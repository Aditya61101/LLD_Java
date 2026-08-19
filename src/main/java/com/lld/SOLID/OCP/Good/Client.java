package com.lld.SOLID.OCP.Good;

public class Client {

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        PaymentMethod pm = new DebitCard();
        processor.processPayment(pm, 100);
    }
}
