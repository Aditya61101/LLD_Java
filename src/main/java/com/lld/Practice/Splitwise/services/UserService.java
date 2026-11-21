package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.BalanceStatus;
import com.lld.Practice.Splitwise.Group;
import com.lld.Practice.Splitwise.User;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public String createUser(String name, String email) {
        if(userRepository.userExistsByEmail(email)) throw new IllegalArgumentException("User already exists!");
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
        if(!userRepository.userExistsById(userId)) {
            throw new IllegalArgumentException("User not found!");
        }
        List<Group> associatedGroups = groupRepository.getGroupsByMemberId(userId);
        // if user owes someone or someone owes user, we won't allow user deletion
        for(Group group: associatedGroups) {
            BalanceStatus status = group.getBalanceStatus(userId);
            if(status != BalanceStatus.CLEAR) return status;
        }
        // after checking all groups, remove the user from all associated groups
        for(Group group: associatedGroups) {
            group.removeUser(userId);
        }
        userRepository.deleteUser(userId);
        return BalanceStatus.CLEAR;
    }
}
