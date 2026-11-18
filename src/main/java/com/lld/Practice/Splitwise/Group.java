package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.*;

public class Group {
    private final String id;
    private String name;
    private final Set<String> members = new HashSet<>();
    private final List<Expense>expenseList = new ArrayList<>();
    private final List<Settlement>settlementList = new ArrayList<>();
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

        BalanceStatus status = balanceSheet.hasOutstandingBalance(userId);
        if(status != BalanceStatus.CLEAR) return status;

        members.remove(userId);
        return status;
    }

    public BalanceStatus hasOutStandingBalance(String userId) {
        return balanceSheet.hasOutstandingBalance(userId);
    }

    public void recordExpense(Expense expense) {
        this.expenseList.add(expense);
        String payerId = expense.getPayerId();
        for(Map.Entry<String, BigDecimal> s: expense.getShares().entrySet()) {
            balanceSheet.addDebt(payerId, s.getKey(), s.getValue());
        }
    }

    public void recordSettlement(Settlement settlement) {
        this.settlementList.add(settlement);
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

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<Settlement> getSettlementList() {
        return settlementList;
    }

    public void checkSettlementValidity(String payerId, String payeeId, BigDecimal amount) {
        balanceSheet.checkSettlementValidity(payerId, payeeId, amount);
    }

}
