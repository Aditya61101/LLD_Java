package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.BalanceStatus;
import com.lld.Practice.Splitwise.Group;
import com.lld.Practice.Splitwise.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    // Map for Email-based lookup
    private final Map<String, User> usersByEmail = new HashMap<>();
    private final GroupRepository groupRepository;

    public InMemoryUserRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

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
        String email = user.getEmail();
        users.put(userId, user);
        usersByEmail.put(email, user);
    }

    public BalanceStatus deleteUser(String userId) {
        if(!this.userExistsById(userId)) {
            throw new IllegalArgumentException("User not found!");
        }
        // if user owes someone or someone owes user, we won't allow user deletion
        for(Group group: groupRepository.getAllGroups().values()) {
            BalanceStatus status = group.getBalanceStatus(userId);
            if(status != BalanceStatus.CLEAR) return status;
        }
        String email = users.get(userId).getEmail();
        usersByEmail.remove(email);
        users.remove(userId);
        return BalanceStatus.CLEAR;
    }

    public boolean userExistsByEmail(String email) {
        return usersByEmail.containsKey(email);
    }

    public boolean userExistsById(String userId) {
        return users.containsKey(userId);
    }
}
