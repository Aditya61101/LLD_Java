package com.lld.Practice.LibraryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    Map<String, User> users;
    Map<String, LibraryItem> items;
    BorrowService borrowService;

    Library() {
        users = new HashMap<>();
        items = new HashMap<>();
        borrowService = new BorrowService(10,30,5);
    }

    public void addUser(User user) {
        String id = user.getId();
        if(users.containsKey(id)) {
            throw new IllegalStateException("User is already present.");
        }
        users.put(user.getId(), user);
    }

    public void removeUser(String userId) {
        try {
            User user = getUser(userId);
users.remove(userId);
            System.out.println("User: "+ user.getName() + " removed.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(LibraryItem libraryItem) {
        String id = libraryItem.getId();
        if(items.containsKey(id)) {
            throw new IllegalStateException("Item is already present.");
        }
        items.put(id, libraryItem);
    }
    public void removeItem(String itemId) {
        try {
            LibraryItem libraryItem = this.getLibraryItem(itemId);
            LibraryItemStatus status = libraryItem.getStatus();
            if(status == LibraryItemStatus.BORROWED || status == LibraryItemStatus.RESERVED) {
                throw new IllegalStateException("Item has already is not available to delete.");
            }
            items.remove(itemId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BorrowedLibraryItem borrowItem(String userId, String itemId) {
        try {
            LibraryItem libraryItem = this.getLibraryItem(itemId);
            User user = this.getUser(userId);
            return borrowService.borrowItem(user, libraryItem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // naive implementation
    public List<LibraryItem> search(String query) {
        if(query==null || query.trim().isEmpty()) {
            return new ArrayList<>(items.values());
        }
        String cleanQuery = query.trim().toLowerCase();
        // Optional: Split into tokens for multi-word matching (e.g., "1984 Orwell")
        String[] tokens = cleanQuery.split("\\s+");
        return items.values().stream()
                .filter(item -> {
                    String title = item.getTitle().toLowerCase();
                    String creator = item.getPrimaryCreator().toLowerCase();
                    String code = item.getStandardCode().toLowerCase();

                    // Strategy A: Simple Contains (If ANY token matches ANY field)
                    // Good for broad search
                    for (String token : tokens) {
                        if (title.contains(token) ||
                                creator.contains(token) ||
                                code.contains(token)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public double returnItem(BorrowedLibraryItem borrowedLibraryItem) {
        return borrowService.returnItem(borrowedLibraryItem);
    }

    private LibraryItem getLibraryItem(String itemId) {
        LibraryItem libraryItem = items.get(itemId);
        if(libraryItem == null) {
            throw new IllegalStateException("No library item found for id: "+ itemId);
        }
        return libraryItem;
    }
    private User getUser(String userId) {
        User user = users.get(userId);
        if(user == null) {
            throw new IllegalStateException("No user found for id: "+ userId);
        }
        return user;
    }

}
