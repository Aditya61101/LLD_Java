package com.lld.Practice.LibraryManagement;

import com.lld.Practice.LibraryManagement.borrowPolicies.BookPolicy;
import com.lld.Practice.LibraryManagement.borrowPolicies.DVDPolicy;
import com.lld.Practice.LibraryManagement.borrowPolicies.MagazinePolicy;
import com.lld.Practice.LibraryManagement.libraryItems.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {

        Library library = new Library(Client.getPolicyMap());
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

    static Map<LibraryItemType, BorrowPolicy> getPolicyMap() {
        Map<LibraryItemType, BorrowPolicy> policyMap = new HashMap<>();
        policyMap.put(LibraryItemType.BOOK, new BookPolicy(30,2, 10));
        policyMap.put(LibraryItemType.DVD, new DVDPolicy(15, 10, 3));
        policyMap.put(LibraryItemType.MAGAZINE, new MagazinePolicy(20, 5, 5));
        return policyMap;
    }
}
