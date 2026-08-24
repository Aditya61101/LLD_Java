package com.lld.Practice.LibraryManagement.services;

import com.lld.Practice.LibraryManagement.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BorrowService {
    private final ReservationService reservationService;
    // userid is the key
    private final Map<String, Set<BorrowedLibraryItem>> borrowedHistory;
    private final Map<String, Integer>activeBorrowCount;
    Map<LibraryItemType, BorrowPolicy> policyMap;
    NotificationService notificationService;
    
    public BorrowService(Map<LibraryItemType, BorrowPolicy> policyMap, NotificationService notificationService) {
        reservationService = new ReservationService();
        this.notificationService = notificationService;
        this.policyMap = policyMap;
        borrowedHistory = new HashMap<>();
        activeBorrowCount = new HashMap<>();
    }

    public BorrowedLibraryItem borrowItem(User user, LibraryItem libItem) {
        String userId = user.getId();
        String libItemId = libItem.getId();
        BorrowPolicy policy = policyMap.get(libItem.getItemType());

        if (activeBorrowCount.getOrDefault(userId, 0) >= policy.getBorrowLimit()) {
            throw new RuntimeException("Borrow limit reached for user: " + user.getName());
        }
        // if item is not available we add user to the queue
        if(libItem.getStatus() != LibraryItemStatus.AVAILABLE) {
            reservationService.addUserToQueue(user, libItem);
        }
        // making instance of borrowed library item
        BorrowedLibraryItem borrowedLibraryItem = new BorrowedLibraryItem(libItem, Instant.now(), user);

        // adding it to the borrowed history of the user
        addLibraryItemToUserHistory(userId, borrowedLibraryItem);

        // increasing the active borrow count by 1
        activeBorrowCount.put(userId, activeBorrowCount.getOrDefault(userId, 0) + 1);

        // if status was RESERVED, removed the front user from the queue since now that user has borrowed it.
        if(libItem.getStatus() == LibraryItemStatus.RESERVED) {
            reservationService.removeUserFromQueue(libItemId);
        }

        // marking library item status as borrowed
        libItem.markAsBorrowed();

        return borrowedLibraryItem;
    }

    public double returnItem(BorrowedLibraryItem borrowedLibraryItem) {
        String userId = borrowedLibraryItem.getUser().getId();
        String libItemId = borrowedLibraryItem.getLibraryItem().getId();
        // mark library status as RESERVED/AVAILABLE based on whether we have people in the queue
        if(reservationService.checkIfQueueEmpty(libItemId)) {
            borrowedLibraryItem.getLibraryItem().markAsAvailable();
        } else {
            borrowedLibraryItem.getLibraryItem().markAsReserved();
        }
        Instant returnedDate = Instant.now();
        // move to fine service
        double fine = this.calculateFine(borrowedLibraryItem, returnedDate);
        
        // decrease active borrow count by 1
        activeBorrowCount.put(userId, activeBorrowCount.getOrDefault(userId, 0) - 1);
        // mark the borrowed item as inactive
        borrowedLibraryItem.deactivate(returnedDate);

        // TODO: notify the first reserved person
        User user = reservationService.getFirstUserInQueue(libItemId);
        LibraryItem libraryItem = borrowedLibraryItem.getLibraryItem();
        notificationService.notify(user, libraryItem);

        return fine;
    }

    private void addLibraryItemToUserHistory(String userId, BorrowedLibraryItem borrowedLibraryItem) {
        borrowedHistory.putIfAbsent(userId, new HashSet<>());
        borrowedHistory.get(userId).add(borrowedLibraryItem);
    }
    private double calculateFine(BorrowedLibraryItem borrowedLibraryItem, Instant returnedDate) {
        BorrowPolicy policy = policyMap.get(borrowedLibraryItem.getLibraryItem().getItemType());
        int borrowDuration = policy.getBorrowDuration();
        int finePerDay = policy.getDelayFinePerDay();
        Instant borrowedDate = borrowedLibraryItem.getBorrowedDate();
        borrowedLibraryItem.setReturnedDate(returnedDate);

        Duration duration = Duration.between(borrowedDate, returnedDate);
        long days = duration.toDays();
        if(days > borrowDuration) {
            return (days-borrowDuration)*finePerDay;
        }
        return 0.0;
    }
}
