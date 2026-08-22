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

    LibraryItemStatus getStatus() {
        return this.status;
    }
    void markAsBorrowed() {
        this.status = LibraryItemStatus.BORROWED;
    }
    void markAsAvailable() {
        this.status = LibraryItemStatus.AVAILABLE;
    }
    void markAsReserved() {
        this.status = LibraryItemStatus.RESERVED;
    }

    String getId() { return this.id; }

    String getTitle() { return this.title; }

    // Unified method for search logic
    public abstract String getStandardCode();

    // Unified method for creator (Author/Director)
    public abstract String getPrimaryCreator();
}
