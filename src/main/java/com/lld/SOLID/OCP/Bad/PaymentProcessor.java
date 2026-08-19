package com.lld.SOLID.OCP.Bad;

public class PaymentProcessor {
    public void processPayment(String method, double amount) {
        if(method.equals("CreditCard")) {
            System.out.println("Processing credit card payment of amount: " + amount);
        } else if(method.equals("PayPal")) {
            System.out.println("Processing PayPal payment of amount: " + amount);
        } else if(method.equals("Bitcoin")) {
            System.out.println("Processing Bitcoin payment of amount: " + amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
        // need to modify this class to add new payment methods, which violates OCP (Open/Closed Principle)
    }
}
