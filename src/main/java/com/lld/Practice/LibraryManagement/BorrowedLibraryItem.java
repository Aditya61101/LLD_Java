package com.lld.Practice.LibraryManagement;

import java.time.Instant;

public class BorrowedLibraryItem {
    LibraryItem libraryItem;
    Instant startDate;
    User user;
    boolean isActive;

    public BorrowedLibraryItem(LibraryItem libraryItem, Instant sd, User u) {
        this.libraryItem = libraryItem;
        this.startDate = sd;
        this.user = u;
        this.isActive = true;
    }
    void deactivate() { this.isActive = false; }
}
