package com.lld.Practice.LibraryManagement;

import com.lld.Practice.LibraryManagement.libraryItems.Book;

import java.util.UUID;

public class Client {
    public static void main(String[] args) {
        Library library = new Library();
        // creating 10 users
        for(int i=1; i<=10; i++) {
            User user = new User("Aditya "+ i);
            library.addUser(user);
        }
        // adding 5 books
        for(int i=1; i<=5; i++) {
            LibraryItem book = new Book("John "+ i, UUID.randomUUID().toString().substring(0,8), "Title "+ i, LibraryItemType.BOOK);
        }
    }
}
