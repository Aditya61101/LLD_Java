package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    // Map for Email-based lookup
    private final Map<String, User> usersByEmail = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
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
        // check through email
        String email = user.getEmail();
        if(this.userExistsByEmail(email)) {
            throw new IllegalArgumentException("User already found!");
        }
        users.put(userId, user);
        usersByEmail.put(email, user);
    }

    public String deleteUser(String userId) {
        if(!userExistsById(userId)) {
            throw new IllegalArgumentException("User not found!");
        }

        // TODO: we also need to check whether there's any outstanding debt on the user or someone owe user or not
        // if user owes someone or someone owes user we won't allow user deletion

        String email = users.get(userId).getEmail();
        usersByEmail.remove(email);
        users.remove(userId);
        return userId;
    }

    private boolean userExistsByEmail(String email) {
        return usersByEmail.containsKey(email);
    }

    private boolean userExistsById(String userId) {
        return users.containsKey(userId);
    }
}
