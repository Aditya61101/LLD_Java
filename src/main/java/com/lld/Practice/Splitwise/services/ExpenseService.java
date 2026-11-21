package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.*;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashSet;
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

    public String createExpense(String groupId, String payerId, SplitType splitType, String description, List<String>participants, Map<String, BigDecimal> allocations, BigDecimal amount, String currency) {
        // 1. get group or throws exception
        Group group = groupRepository.getGroup(groupId);
        // 2. common validations
        Currency CURRENCY = Currency.getInstance(currency);
        int scale = CURRENCY.getDefaultFractionDigits();
        this.checkExpenseValidity(amount, description, participants, payerId, allocations, splitType, scale);
        // 4. get shares based on splitType
        SplitStrategy splitStrategy = splitStrategyRegistry.getSplit(splitType);
        Map<String, BigDecimal> shares = splitStrategy.split(amount, participants, allocations, scale);
        // 5. create expense
        Expense expense = new Expense(payerId, description, amount, splitType, shares, CURRENCY);
        // 6. record expense
        group.recordExpense(expense);
        return expense.getId();
    }
    public void renameDescription(String expenseId, String groupId, String description) {
        Group group = groupRepository.getGroup(groupId);
        Expense expense = group.getExpense(expenseId);
        expense.renameDescription(description);
    }
    private void checkExpenseValidity(BigDecimal amount, String description, List<String>participants, String payerId, Map<String, BigDecimal> allocations, SplitType splitType, int scale) {
        // amount should be positive
        if(amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Expense amount should be positive");
        try {
            // setScale(2) means represent the number with two digits after the decimal point.
            // example 100->100.00, 100.2 -> 100.20, 100.055 -> raises exception
            amount.setScale(scale, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid split");
        }
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Expense description is required");

        if (splitType == null) throw new IllegalArgumentException("Split type is required");

        // checks whether payer exists
        if(!userRepository.userExistsById(payerId)) throw new IllegalArgumentException("Payer doesn't exists");

        // at least one participant should be there
        if (participants == null || participants.isEmpty()) throw new IllegalArgumentException("At least one participant is required.");

        // checks whether ids are not "", " " or null
        if (participants.stream().anyMatch(id -> id == null || id.isBlank())) {
            throw new IllegalArgumentException("Participant id must be a valid string");
        }
        // checks for unique participants
        if (new HashSet<>(participants).size() != participants.size()) {
            throw new IllegalArgumentException("Duplicate participants present");
        }
        // checks for participants existence
        for(String userId:participants) {
            if(!userRepository.userExistsById(userId)) throw new IllegalArgumentException("Participant doesn't exists");
        }
        // for percentage and exact we need allocations of each participant and it should match participants
        if (splitType != SplitType.EQUAL) {
            if(allocations == null || allocations.isEmpty()) throw new IllegalArgumentException("Allocations cannot be empty for: "+ splitType + " split.");
            if(!allocations.keySet().equals(new HashSet<>(participants))) throw new IllegalArgumentException("Allocations must exactly match participants for: "+ splitType + " split.");
        } else if (allocations != null && !allocations.isEmpty()) {
            throw new IllegalArgumentException("Equal split does not accept allocations");
        }
    }
}
