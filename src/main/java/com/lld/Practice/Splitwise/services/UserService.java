package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.BalanceStatus;
import com.lld.Practice.Splitwise.User;
import com.lld.Practice.Splitwise.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(String name, String email) {
        if(userRepository.userExistsByEmail(email)) {
            throw new IllegalArgumentException("User already exists!");
        }
        User user = new User(name, email);
        userRepository.saveUser(user);
        return user.getId();
    }

    public void renameUser(String name, String userId) {
        User user = userRepository.getUser(userId);
        user.renameUser(name);
    }

    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }

    public BalanceStatus deleteUser(String userId) {
        return userRepository.deleteUser(userId);
    }
}
