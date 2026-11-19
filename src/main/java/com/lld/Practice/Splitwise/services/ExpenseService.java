package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.*;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final SplitStrategyRegistry splitStrategyRegistry;

    public ExpenseService(GroupRepository groupRepository, UserRepository userRepository, SplitStrategyRegistry splitStrategyRegistry) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.splitStrategyRegistry = splitStrategyRegistry;
    }

    public void createExpense(String groupId, String payerId, SplitType splitType, String description, List<String>participants, List<BigDecimal>contributions, BigDecimal amount) {
        try {
            // 1. amount should be positive
            if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new RuntimeException("Expense amount should be positive");
            // 2. checks whether payer exists
            if(!userRepository.userExistsById(payerId)) throw new IllegalArgumentException("Payer doesn't exists");
            // 3. get group or throws exception
            Group group = groupRepository.getGroup(groupId);
            // 4. checks whether participants exist and belong to the group
            this.checkParticipantsExistence(participants, group);
            // 5. get shares based on splitType
            SplitStrategy splitStrategy = splitStrategyRegistry.getSplit(splitType);
            Map<String, BigDecimal> shares = splitStrategy.split(amount, participants, contributions);
            // 6. create expense
            Expense expense = new Expense(payerId, description, amount, splitType, shares);
            // 7. record expense
            group.recordExpense(expense);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void renameDescription(String expenseId, String groupId, String description) {
        Group group = groupRepository.getGroup(groupId);
        Expense expense = group.getExpense(expenseId);
        expense.renameDescription(description);
    }
    private void checkParticipantsExistence(List<String>participants, Group group) {
        for(String userId:participants) {
            if(!userRepository.userExistsById(userId)) throw new IllegalArgumentException("Participant doesn't exists");
            if(!group.isGroupMember(userId)) throw new IllegalArgumentException("Participant not a member of the group");
        }
    }
}
