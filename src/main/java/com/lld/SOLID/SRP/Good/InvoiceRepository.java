package com.lld.SOLID.SRP.Good;

public class InvoiceRepository {

    public void saveToDatabase(double amount) {
        System.out.println("Saving to db: " + amount);
    }
}
