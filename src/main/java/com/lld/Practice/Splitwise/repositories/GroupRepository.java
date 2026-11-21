package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.Group;

import java.util.List;
import java.util.Map;

public interface GroupRepository {
    public void saveGroup(Group group);
    public Group getGroup(String groupId);
    public void deleteGroup(String groupId);
    public List<Group> getGroupsByMemberId(String userId);
}
