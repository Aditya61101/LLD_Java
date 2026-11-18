package com.lld.Practice.Splitwise;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private final String email;

    public User(String name, String email) {
        this.id = "U-"+ UUID.randomUUID();
        this.name = name;
        this.email = email;
    }
    public void renameUser(String name) {
        this.name = name;
    }
    public String getId() {return id;}

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
