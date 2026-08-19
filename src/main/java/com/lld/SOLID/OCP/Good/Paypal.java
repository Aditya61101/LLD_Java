package com.lld.SOLID.OCP.Good;

public class Paypal implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying using Paypal");
    }
}
