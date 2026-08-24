package com.lld.Practice.LibraryManagement;

import java.time.Instant;

public class BorrowedLibraryItem {
    LibraryItem libraryItem;
    Instant borrowedDate;
    Instant returnedDate;
    User user;

    public BorrowedLibraryItem(LibraryItem libraryItem, Instant sd, User u) {
        this.libraryItem = libraryItem;
        this.borrowedDate = sd;
        this.user = u;
        this.returnedDate = null;
    }
    public void deactivate(Instant time) { this.returnedDate = time; }

    public LibraryItem getLibraryItem() { return this.libraryItem; }
    public User getUser() { return this.user; }
    public Instant getBorrowedDate() { return this.borrowedDate; }
    public Instant getReturnedDate() { return this.returnedDate; }

    public void setReturnedDate(Instant time) { this.borrowedDate = time; }
}
