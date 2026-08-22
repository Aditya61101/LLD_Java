package com.lld.Practice.LibraryManagement;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BorrowService {
    // library item is the key
    private final Map<String, Queue<User>> reserveQueue;
    // userid is the key
    private final Map<String, Set<BorrowedLibraryItem>> borrowedHistory;
    private final Map<String, Integer>activeBorrowCount;

    private static Integer borrowLimit;
    private static Integer borrowDuration;
    private static Integer finePerDay;
    
    BorrowService(Integer borrowLimit, Integer borrowDuration, Integer finePerDay) {
        reserveQueue = new HashMap<>();
        borrowedHistory = new HashMap<>();
        activeBorrowCount = new HashMap<>();
        BorrowService.borrowLimit = borrowLimit;
        BorrowService.borrowDuration = borrowDuration;
        BorrowService.finePerDay = finePerDay;
    }

    public BorrowedLibraryItem borrowItem(User user, LibraryItem libItem) {
        String userId = user.getId();
        String libItemId = libItem.getId();

        if (activeBorrowCount.getOrDefault(userId, 0) >= borrowLimit) {
            throw new RuntimeException("Borrow limit reached for user: " + user.getName());
        }
        LibraryItemStatus status = libItem.getStatus();
        if(status == LibraryItemStatus.BORROWED) {
            addUserToQueue(user, libItemId);
            // marking the library item as reserved
            libItem.markAsReserved();
            throw new RuntimeException("Item is currently borrowed. User " + user.getName() + " has been added to the reserve queue.");
        }
        // if the item is reserved and the user is not the person from whom its reserved
        // then we put that user into the item's queue
        if(status == LibraryItemStatus.RESERVED && !(!reserveQueue.get(libItemId).isEmpty() && reserveQueue.get(libItemId).peek().getId().equals(userId))) {
            // add user to queue
            addUserToQueue(user, libItemId);
            throw new RuntimeException("Item is currently reserved. User " + user.getName() + " has been added to the reserve queue.");
        }

        // making instance of borrowed library item
        BorrowedLibraryItem borrowedLibraryItem = new BorrowedLibraryItem(libItem, Instant.now(), user);

        // adding it to the borrowed history of the user
        addLibraryItemToUserHistory(userId, borrowedLibraryItem);

        // increasing the active borrow count by 1
        activeBorrowCount.put(userId, activeBorrowCount.getOrDefault(userId, 0) + 1);

        // if status was RESERVED, removed the front user from the queue since now that user has borrowed it.
        if(libItem.status == LibraryItemStatus.RESERVED) {
            removeUserFromQueue(libItemId);
        }

        // marking library item status as borrowed
        libItem.markAsBorrowed();

        return borrowedLibraryItem;
    }

    public double returnItem(BorrowedLibraryItem borrowedLibraryItem) {
        String userId = borrowedLibraryItem.user.getId();
        String libItemId = borrowedLibraryItem.libraryItem.getId();
        // mark library status as RESERVED/AVAILABLE based on whether we have people in the queue
        if(reserveQueue.get(libItemId).isEmpty()) {
            borrowedLibraryItem.libraryItem.markAsAvailable();
        } else {
            borrowedLibraryItem.libraryItem.markAsReserved();
        }
        double fine = this.calculateFine(borrowedLibraryItem);
        
        // decrease active borrow count by 1
        activeBorrowCount.put(userId, activeBorrowCount.getOrDefault(userId, 0) - 1);
        // mark the borrowed item as inactive
        borrowedLibraryItem.deactivate();
        // TODO: notify the first reserved person
        return fine;
    }

    private void addUserToQueue(User user, String libItemId) {
        // add user to queue
        reserveQueue.putIfAbsent(libItemId, new LinkedList<>());
        reserveQueue.get(libItemId).add(user);
    }
    private void removeUserFromQueue(String libItemId) {
        reserveQueue.get(libItemId).poll();
    }
    private void addLibraryItemToUserHistory(String userId, BorrowedLibraryItem borrowedLibraryItem) {
        borrowedHistory.putIfAbsent(userId, new HashSet<>());
        borrowedHistory.get(userId).add(borrowedLibraryItem);
    }
    private double calculateFine(BorrowedLibraryItem borrowedLibraryItem) {
        Instant startDate = borrowedLibraryItem.startDate;
        Duration duration = Duration.between(startDate, Instant.now());
        long days = duration.toDays();
        if(days > borrowDuration) {
            return (borrowDuration-days)*finePerDay;
        }
        return 0.0;
    }
}
