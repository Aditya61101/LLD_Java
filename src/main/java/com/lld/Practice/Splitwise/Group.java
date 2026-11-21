package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.*;

public class Group {
    private final String id;
    private String name;
    private final Set<String> members = new HashSet<>();
    private final Map<String, Expense>expenses = new HashMap<>();
    private final Map<String, Settlement>settlements = new HashMap<>();
    private final BalanceSheet balanceSheet;

    public Group(String name, String creatorId) {
        this.id = "GR-"+ UUID.randomUUID();
        this.name = name;
        this.balanceSheet = new BalanceSheet();
        this.members.add(creatorId);
    }

    public void renameGroup(String name) {
        this.name = name;
    }

    public void addUser(String userId) {
        if(members.contains(userId)) throw new IllegalArgumentException("User already present in group");
        members.add(userId);
    }

    public BalanceStatus removeUser(String userId) {
        if(!members.contains(userId)) throw new IllegalArgumentException("User not present in group");

        BalanceStatus status = balanceSheet.getBalanceStatus(userId);
        if(status != BalanceStatus.CLEAR) return status;

        members.remove(userId);
        return status;
    }

    public BalanceStatus getBalanceStatus(String userId) {
        return balanceSheet.getBalanceStatus(userId);
    }

    public void recordExpense(Expense expense) {
        this.expenses.put(expense.getId(), expense);
        String payerId = expense.getPayerId();
        for(Map.Entry<String, BigDecimal> s: expense.getShares().entrySet()) {
if (!payerId.equals(s.getKey())) {
    balanceSheet.addDebt(payerId, s.getKey(), s.getValue());
}
        }
    }

    public void recordSettlement(Settlement settlement) {
        this.settlements.put(settlement.getId(), settlement);
        String payerId = settlement.getPayerId();
        String payeeId = settlement.getPayeeId();
        BigDecimal amount = settlement.getAmount();
        balanceSheet.settleDebt(payerId, payeeId, amount);
    }

    public boolean isGroupMember(String userId) {
        return members.contains(userId);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Expense> getExpenses() {
        return expenses;
    }

    public Map<String, Settlement> getSettlements() {
        return settlements;
    }

    public void checkSettlementValidity(String payerId, String payeeId, BigDecimal amount) {
        balanceSheet.checkSettlementValidity(payerId, payeeId, amount);
    }
    public Expense getExpense(String expenseId) {
        if(!expenses.containsKey(expenseId)) throw new IllegalArgumentException("Expense not found!");
        return expenses.get(expenseId);
    }
}
