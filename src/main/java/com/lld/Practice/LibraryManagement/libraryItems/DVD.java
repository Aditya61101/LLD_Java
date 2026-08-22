package com.lld.Practice.LibraryManagement.libraryItems;

import com.lld.Practice.LibraryManagement.LibraryItem;
import com.lld.Practice.LibraryManagement.LibraryItemType;

public class DVD extends LibraryItem {
    private final String upc;
    private final String director;

    public DVD(String director, String upc, String title, LibraryItemType itemType) {
        super(title, itemType);
        this.upc = upc;
        this.director = director;
    }

    @Override
    public String getStandardCode() {
        return upc;
    }

    @Override
    public String getPrimaryCreator() {
        return director;
    }

}
