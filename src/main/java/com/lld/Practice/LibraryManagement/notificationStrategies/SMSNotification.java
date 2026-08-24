package com.lld.Practice.LibraryManagement.notificationStrategies;

import com.lld.Practice.LibraryManagement.LibraryItem;
import com.lld.Practice.LibraryManagement.NotificationChannel;
import com.lld.Practice.LibraryManagement.User;

public class SMSNotification implements NotificationChannel {
    public void send(User user, LibraryItem libraryItem) {
        System.out.println("Send via SMS: Dear " + user.getName() + ", item " + libraryItem.getTitle() + "is now available.");
    }
}
