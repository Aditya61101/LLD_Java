package com.lld.SOLID.SRP.Bad;

public class Invoice {
    private double amount;
    public Invoice(double amount) {
        this.amount = amount;
    }
    public void generateInvoice() {
        System.out.println("Generating invoice for amount: " + amount);
    }

    public void saveToDatabase() {
        System.out.println("Saving to db: " + amount);
    }

    public void sendEmail() {
        System.out.println("Sending email for amount: " + amount);
    }
}
