package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.BalanceStatus;
import com.lld.Practice.Splitwise.Expense;
import com.lld.Practice.Splitwise.Group;
import com.lld.Practice.Splitwise.Settlement;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.util.List;
import java.util.Map;

public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public String createGroup(String creatorId, String name) {
        if(!userRepository.userExistsById(creatorId)) throw new IllegalArgumentException("Creator does not exists");
        Group group = new Group(name, creatorId);
        groupRepository.saveGroup(group);
        return group.getId();
    }
    public void joinGroup(String groupId, String userId) {
        if(!userRepository.userExistsById(userId)) throw new IllegalArgumentException("User does not exists.");
        Group group = groupRepository.getGroup(groupId);
        if(group.isGroupMember(userId)) throw new IllegalArgumentException("User is already the member of this group.");
        group.addUser(userId);
    }
    public BalanceStatus exitGroup(String groupId, String userId) {
        if(!userRepository.userExistsById(userId)) throw new IllegalArgumentException("User does not exists.");
        Group group = groupRepository.getGroup(groupId);
        if(!group.isGroupMember(userId)) throw new IllegalArgumentException("User is not the member of this group.");
        return group.removeUser(userId);
    }

    public void deleteGroup(String groupId) {
        Group group = groupRepository.getGroup(groupId);
        if(!group.isSettled()) throw new IllegalStateException("Group still has some unsettled debts.");
        groupRepository.deleteGroup(groupId);
    }

    public Map<String, Expense> getAllExpenses(String groupId) {
        Group group = groupRepository.getGroup(groupId);
        return group.getExpenses();
    }
    public Map<String, Settlement> getAllSettlements(String groupId) {
        Group group = groupRepository.getGroup(groupId);
        return group.getSettlements();
    }
}
