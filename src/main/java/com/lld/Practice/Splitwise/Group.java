package com.lld.Practice.Splitwise;

import java.util.*;

public class Group {
    private final String id;
    private String name;
    private final Set<String> members = new HashSet<>();
    private final List<Expense>expenseList = new ArrayList<>();
    private final List<Settlement>settlementList = new ArrayList<>();
    private final BalanceSheet balanceSheet;

    public Group(String name) {
        this.id = "GR-"+ UUID.randomUUID();
        this.name = name;
        this.balanceSheet = new BalanceSheet();
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

    }

    public void recordSettlement(Settlement settlement) {

    }
}
