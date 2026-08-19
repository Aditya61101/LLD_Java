package com.lld.SOLID.DIP.Good;

public class Client {
    public static void main(String[] args) {
        NotificationService emailNotification = new NotificationService(new EmailChannel());
        emailNotification.notify("Your order has been placed!");

        NotificationService smsNotification = new NotificationService(new SMSChannel());
        smsNotification.notify("Hello, your OTP is 1248");
    }
}
