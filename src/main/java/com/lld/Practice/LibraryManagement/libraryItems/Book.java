package com.lld.Practice.LibraryManagement.libraryItems;

import com.lld.Practice.LibraryManagement.LibraryItem;
import com.lld.Practice.LibraryManagement.LibraryItemType;

public class Book extends LibraryItem {
    private final String author;
    private final String isbn;

    public Book(String author, String isbn, String title, LibraryItemType itemType) {
        super(title, itemType);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String getStandardCode() {
        return isbn;
    }

    @Override
    public String getPrimaryCreator() {
        return author;
    }

}
