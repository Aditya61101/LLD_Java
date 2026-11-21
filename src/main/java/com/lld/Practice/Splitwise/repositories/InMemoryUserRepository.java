package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    // Map for Email-based lookup
    private final Map<String, User> usersByEmail = new HashMap<>();

    public Map<String, User> getUsers() {
        return Map.copyOf(users);
    }

    public User getUser(String userId) {
        if(!this.userExistsById(userId)) {
            throw new IllegalArgumentException("User not found!");
        }
        return users.get(userId);
    }

    public User getUserByEmail(String email) {
        if(!this.userExistsByEmail(email)) {
            throw new IllegalArgumentException("User not found!");
        }
        return usersByEmail.get(email);
    }

    public void saveUser(User user) {
        String userId = user.getId();
        String email = user.getEmail();
        users.put(userId, user);
        usersByEmail.put(email, user);
    }

    public void deleteUser(String userId) {
        String email = users.get(userId).getEmail();
        usersByEmail.remove(email);
        users.remove(userId);
    }

    public boolean userExistsByEmail(String email) {
        return usersByEmail.containsKey(email);
    }

    public boolean userExistsById(String userId) {
        return users.containsKey(userId);
    }
}
