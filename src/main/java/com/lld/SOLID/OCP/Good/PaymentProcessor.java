package com.lld.SOLID.OCP.Good;

public class PaymentProcessor {
    public void processPayment(PaymentMethod pm, double amount) {
        // runtime polymorphism
        pm.pay(amount);
    }
}
