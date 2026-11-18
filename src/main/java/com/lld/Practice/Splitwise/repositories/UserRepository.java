package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.BalanceStatus;
import com.lld.Practice.Splitwise.User;

import java.util.Map;

public interface UserRepository {
    public Map<String, User> getUsers();
    public User getUserByEmail(String email);
    public User getUser(String userId);
    public void saveUser(User user);
    public BalanceStatus deleteUser(String userId);
    public boolean userExistsById(String userId);

}
