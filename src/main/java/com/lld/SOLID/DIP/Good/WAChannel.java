package com.lld.SOLID.DIP.Good;

public class WAChannel implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("Sending WhatsApp message: " + message);
    }
}
