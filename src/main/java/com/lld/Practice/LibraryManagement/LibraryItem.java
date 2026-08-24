package com.lld.Practice.LibraryManagement;

import java.util.UUID;

abstract public class LibraryItem {
    String id;
    String title;
    LibraryItemStatus status;
    LibraryItemType itemType;

    protected LibraryItem(String title, LibraryItemType itemType) {
        id = "LI-" + UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.status = LibraryItemStatus.AVAILABLE;
        this.itemType = itemType;
    }

    public LibraryItemStatus getStatus() {
        return this.status;
    }
    public void markAsBorrowed() {
        this.status = LibraryItemStatus.BORROWED;
    }
    public void markAsAvailable() {
        this.status = LibraryItemStatus.AVAILABLE;
    }
    public void markAsReserved() {
        this.status = LibraryItemStatus.RESERVED;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public LibraryItemType getItemType() { return this.itemType; }

    // Unified method for search logic
    public abstract String getStandardCode();

    // Unified method for creator (Author/Director)
    public abstract String getPrimaryCreator();
}
