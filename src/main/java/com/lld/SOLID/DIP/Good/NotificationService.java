package com.lld.SOLID.DIP.Good;

public class NotificationService {
    private final NotificationChannel notificationChannel;

    NotificationService(NotificationChannel nc) {
        this.notificationChannel = nc;
    }

    public void notify(String message) {
        notificationChannel.send(message);
    }
}
