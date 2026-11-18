package com.lld.Practice.Splitwise.repositories;

import com.lld.Practice.Splitwise.Group;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGroupRepository implements GroupRepository {
    Map<String, Group> groups = new HashMap<>();

}
