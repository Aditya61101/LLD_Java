package com.lld.SOLID.OCP.Good;

public class DebitCard implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying "+ amount + " using Debit Card.");
    }
}
