package com.lld.Practice.Splitwise;

import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.InMemoryGroupRepository;
import com.lld.Practice.Splitwise.repositories.InMemoryUserRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;
import com.lld.Practice.Splitwise.services.ExpenseService;
import com.lld.Practice.Splitwise.services.GroupService;
import com.lld.Practice.Splitwise.services.SettlementService;
import com.lld.Practice.Splitwise.services.UserService;
import com.lld.Practice.Splitwise.splitStrategies.EqualSplit;
import com.lld.Practice.Splitwise.splitStrategies.ExactSplit;
import com.lld.Practice.Splitwise.splitStrategies.PercentageSplit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SplitWiseSystem {
    private final ExpenseService expenseService;
    private final GroupService groupService;
    private final UserService userService;
    private final SettlementService settlementService;

    SplitWiseSystem() {
        GroupRepository groupRepository = new InMemoryGroupRepository();
        UserRepository userRepository = new InMemoryUserRepository();

        SplitStrategyRegistry splitStrategyRegistry = new SplitStrategyRegistry();
        splitStrategyRegistry.register(SplitType.EQUAL, new EqualSplit());
        splitStrategyRegistry.register(SplitType.EXACT, new ExactSplit());
        splitStrategyRegistry.register(SplitType.PERCENTAGE, new PercentageSplit());

        this.expenseService = new ExpenseService(groupRepository, userRepository, splitStrategyRegistry);
        this.groupService = new GroupService(groupRepository, userRepository);
        this.userService = new UserService(userRepository, groupRepository);
        this.settlementService = new SettlementService(groupRepository, userRepository);
    }
    // User CRUD
    public String createUser(String name, String email) {
        return this.userService.createUser(name, email);
    }
    public void renameUser(String name, String userId) {
        this.userService.renameUser(name, userId);
    }
    public User getUserById(String userId) {
        return this.userService.getUser(userId);
    }
    public BalanceStatus deleteUser(String userId) {
        return this.userService.deleteUser(userId);
    }
    // Group CRUD
    public String createGroup(String creatorId, String name) {
        return this.groupService.createGroup(creatorId, name);
    }
    public void joinGroup(String groupId, String userId) {
        this.groupService.joinGroup(groupId, userId);
    }
    public BalanceStatus exitGroup(String groupId, String userId) {
        return groupService.exitGroup(groupId, userId);
    }
    public void deleteGroup(String groupId) {
        groupService.deleteGroup(groupId);
    }
    // Expense CRUD
    public void createExpense(String groupId, String payerId, SplitType splitType, String description, List<String> participants, Map<String, BigDecimal> allocations, BigDecimal amount) {
        expenseService.createExpense(groupId, payerId, splitType, description, participants, allocations, amount);
    }
    public void renameDescription(String expenseId, String groupId, String description) {
        expenseService.renameDescription(expenseId, groupId, description);
    }
    // Settlement CRUD
    public void createSettlement(String payerId, String payeeId, BigDecimal amount, String groupId) {
        settlementService.createSettlement(payerId, payeeId, amount, groupId);
    }
}
