package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryGroupRepository implements GroupRepository {
    Map<String, Group> groups = new HashMap<>();

    public void saveGroup(Group group) {
        groups.put(group.getId(), group);
    }
    public Group getGroup(String groupId) {
        if(!this.groupExists(groupId)) throw new IllegalArgumentException("Group doesn't exists");
        return groups.get(groupId);
    }
    public void deleteGroup(String groupId) {
        if(!this.groupExists(groupId)) throw new IllegalArgumentException("Group doesn't exists");
        // delete all expenses, settlements, balances, entry from groups map
        groups.remove(groupId);
    }
    public List<Group> getGroupsByMemberId(String userId) {
        return groups.values().stream().filter(g -> (g.isGroupMember(userId))).toList();
    }
    private boolean groupExists(String groupId) {
        return groups.containsKey(groupId);
    }
}
