package com.lld.Practice.LibraryManagement.services;

import com.lld.Practice.LibraryManagement.LibraryItem;
import com.lld.Practice.LibraryManagement.LibraryItemStatus;
import com.lld.Practice.LibraryManagement.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ReservationService {
    // library item is the key
    private final Map<String, Queue<User>> reserveQueue;

    public ReservationService() {
        reserveQueue = new HashMap<>();
    }

    public void addUserToQueue(User user, LibraryItem libraryItem) {
        String libItemId = libraryItem.getId();
        String userId = user.getId();
        LibraryItemStatus status = libraryItem.getStatus();

        // add user to queue
        reserveQueue.putIfAbsent(libItemId, new LinkedList<>());
        reserveQueue.get(libItemId).add(user);

        if(status == LibraryItemStatus.BORROWED) {
            throw new RuntimeException("Item is currently borrowed. User " + user.getName() + " has been added to the reserve queue.");
        }
        // if the item is reserved and the user is not the person from whom its reserved
        // then we put that user into the item's queue
        if((status == LibraryItemStatus.RESERVED) && !(!this.checkIfQueueEmpty(libItemId) && getFirstUserInQueue(libItemId).getId().equals(userId))) {
            throw new RuntimeException("Item is currently reserved. User " + user.getName() + " has been added to the reserve queue.");
        }
    }

    public void removeUserFromQueue(String libItemId) {
        reserveQueue.get(libItemId).poll();
    }

    public boolean checkIfQueueEmpty(String libItemId) {
        return reserveQueue.get(libItemId).isEmpty();
    }

    public User getFirstUserInQueue(String libItemId) {
        return reserveQueue.get(libItemId).peek();
    }
}
