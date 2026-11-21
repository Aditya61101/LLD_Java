package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private final Map<String, Map<String, BigDecimal>> balance = new HashMap<>();

    protected BalanceStatus getBalanceStatus(String userId) {
        if(balance.containsKey(userId)) return BalanceStatus.DEBTOR;
        for(Map<String, BigDecimal> creditor: balance.values()) {
            if(creditor.containsKey(userId)) return BalanceStatus.CREDITOR;
        }
        return BalanceStatus.CLEAR;
    }
    protected void addDebt(String payerId, String payeeId, BigDecimal amount) {
        BigDecimal debtAmount = payerDebtOnPayee(payerId, payeeId);
        int comparison = debtAmount.compareTo(amount);
        if (debtAmount.signum() == 0 || comparison < 0) {
            // Payee owes payer the net remaining amount.
            BigDecimal remaining = amount.subtract(debtAmount);
            if (debtAmount.signum() > 0) {
                removeDebt(payerId, payeeId);
            }
            balance.computeIfAbsent(payeeId, k -> new HashMap<>()).merge(payerId, remaining, BigDecimal::add);
        } else if (comparison > 0) {
            // Payer still owes payee.
            balance.get(payerId).put(payeeId, debtAmount.subtract(amount));

        } else {
            // Debts cancel completely.
            removeDebt(payerId, payeeId);
        }
    }
    protected void settleDebt(String payerId, String payeeId, BigDecimal amount) {
        Map<String, BigDecimal> debts = balance.get(payerId);
        BigDecimal remaining = debts.get(payeeId).subtract(amount);
        if (remaining.signum() == 0) {
            removeDebt(payerId, payeeId);
        } else {
            debts.put(payeeId, remaining);
        }
    }
    protected void validateSettlement(String payerId, String payeeId, BigDecimal amount) {
        if(!balance.containsKey(payerId)) throw new IllegalArgumentException("Payer doesn't owe to anyone");
        Map<String, BigDecimal> debts = balance.get(payerId);
        if(!debts.containsKey(payeeId)) throw new IllegalArgumentException("Payer doesn't owe to payee");
        BigDecimal debt = debts.get(payeeId);
        if(debt.compareTo(amount) < 0) throw new IllegalArgumentException("Debt settlement amount cannot be greater than debt itself. Actual debt: " + debt);
    }
    private BigDecimal payerDebtOnPayee(String payerId, String payeeId) {
        if(!balance.containsKey(payerId)) return BigDecimal.valueOf(0);
        Map<String, BigDecimal> debts = balance.get(payerId);
        return debts.getOrDefault(payeeId, BigDecimal.valueOf(0));
    }

    private void removeDebt(String debtorId, String creditorId) {
        Map<String, BigDecimal> debts = balance.get(debtorId);
        if (debts == null) return;

        debts.remove(creditorId);
        if (debts.isEmpty()) balance.remove(debtorId);
    }
}
