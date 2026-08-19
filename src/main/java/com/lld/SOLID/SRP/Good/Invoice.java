package com.lld.SOLID.SRP.Good;
// SRP: A class should have only one reason to change.
public class Invoice {
    private double amount;

    Invoice(double amount) {
        this.amount = amount;
    }

    public void generateInvoice() {
        System.out.println("Generating invoice for amount: " + amount);
    }
}
