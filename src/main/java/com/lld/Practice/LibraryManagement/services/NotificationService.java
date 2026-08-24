package com.lld.Practice.LibraryManagement.services;

import com.lld.Practice.LibraryManagement.LibraryItem;
import com.lld.Practice.LibraryManagement.NotificationChannel;
import com.lld.Practice.LibraryManagement.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService {
    private final Map<String, List<NotificationChannel>> userChannelMap;
    public NotificationService() {
        userChannelMap = new HashMap<>();
    }
    public void notify(User user, LibraryItem libraryItem) {
        for(NotificationChannel channel: userChannelMap.get(user.getId())) {
            channel.send(user, libraryItem);
        }
    }
    public void addChannelForUser(String userId, NotificationChannel channel) {
        userChannelMap.putIfAbsent(userId, new ArrayList<>());
        userChannelMap.get(userId).add(channel);
    }
}
