package com.lld.Practice.LibraryManagement;

import com.lld.Practice.LibraryManagement.libraryItems.Book;

import java.util.List;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("Aditya");
        // adding 5 books
        for(int i=1; i<=5; i++) {
            LibraryItem book = new Book("John "+ i, UUID.randomUUID().toString().substring(0,8), "Title "+ i, LibraryItemType.BOOK);
        }

        String query = "Title 2";
        List<LibraryItem> searchedItems = library.search(query);
        String itemId = searchedItems.get(0).getId();
        BorrowedLibraryItem borrowedLibraryItem = library.borrowItem(itemId, user.getId());
    }
}
