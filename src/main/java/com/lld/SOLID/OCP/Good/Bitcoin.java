package com.lld.SOLID.OCP.Good;

public class Bitcoin implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying using Bitcoin");
    }
}
