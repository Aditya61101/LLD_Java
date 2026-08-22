package com.lld.Practice.LibraryManagement;

import java.util.UUID;

public class User {
    private final String id;
    private final String name;

    User(String name) {
        this.id = "U-" + UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
    }

    public String getName() { return this.name; }
    public String getId() {return this.id; }
}
