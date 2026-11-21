package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Empty group name not allowed.");
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
        if (expense == null) throw new IllegalArgumentException("Expense cannot be null");

        String payerId = expense.getPayerId();
        Map<String, BigDecimal> shares = expense.getShares();
        BigDecimal amount = expense.getAmount();
        Currency currency = expense.getCurrency();
        int scale = currency.getDefaultFractionDigits();
        validateExpense(amount, payerId, shares, scale);

        for(Map.Entry<String, BigDecimal> s: expense.getShares().entrySet()) {
            if (!payerId.equals(s.getKey()) && s.getValue().signum() > 0) {
                balanceSheet.addDebt(payerId, s.getKey(), s.getValue());
            }
        }
        this.expenses.put(expense.getId(), expense);
    }

    public void recordSettlement(Settlement settlement) {
        String payerId = settlement.getPayerId();
        String payeeId = settlement.getPayeeId();
        if(!members.contains(payeeId)) throw new IllegalArgumentException(payeeId+" is not a member of this group");
        if(!members.contains(payerId)) throw new IllegalArgumentException(payerId+" is not a member of this group");

        BigDecimal amount = settlement.getAmount();
        Currency currency = settlement.getCurrency();
        int scale = currency.getDefaultFractionDigits();
        validateAmount(amount, scale);
        // 4. check whether amount <= actual debt of payee on payer
        balanceSheet.validateSettlement(payerId, payeeId, amount);
        balanceSheet.settleDebt(payerId, payeeId, amount);
        settlements.put(settlement.getId(), settlement);
    }

    public boolean isGroupMember(String userId) { return members.contains(userId); }

    public String getId() { return id; }

    public String getName() { return name; }

    public Map<String, Expense> getExpenses() { return Map.copyOf(expenses); }

    public Map<String, Settlement> getSettlements() { return Map.copyOf(settlements); }

    public Expense getExpense(String expenseId) {
        if(!expenses.containsKey(expenseId)) throw new IllegalArgumentException("Expense not found!");
        return expenses.get(expenseId);
    }
    public boolean isSettled() {
        return balanceSheet.isSettled();
    }
    private void validateAmount(BigDecimal amount, int scale) {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Invalid amount");
        try {
            amount.setScale(scale, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid amount precision");
        }
    }
    private void validateExpense(BigDecimal amount, String payerId, Map<String, BigDecimal> shares, int scale) {
        validateAmount(amount, scale);
        if (shares == null || shares.isEmpty()) {
            throw new IllegalArgumentException("Shares cannot be empty");
        }

        // payer should exist in group
        if(!members.contains(payerId)) throw new IllegalArgumentException("Payer is not a member of the group");

        BigDecimal totalShares = BigDecimal.ZERO;
        // Phase 1: Validate everything
        for (Map.Entry<String, BigDecimal> entry : shares.entrySet()) {

            String participantId = entry.getKey();
            BigDecimal share = entry.getValue();

            if (!members.contains(participantId)) throw new IllegalArgumentException("Participant must belong to the group");

            if (share == null || share.signum() < 0) throw new IllegalArgumentException("Invalid share amount");

            try {
                share.setScale(scale, RoundingMode.UNNECESSARY);
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Invalid share precision");
            }
            totalShares = totalShares.add(share);
        }
        if (totalShares.compareTo(amount) != 0) {
            throw new IllegalArgumentException("Sum of shares must equal expense amount");
        }
    }
}
