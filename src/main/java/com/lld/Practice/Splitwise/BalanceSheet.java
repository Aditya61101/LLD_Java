package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private final Map<String, Map<String, BigDecimal>> balance = new HashMap<>();

    protected BalanceStatus hasOutstandingBalance(String userId) {
        return BalanceStatus.CLEAR;
    }
    protected void addDebt(String payerId, String payeeId, BigDecimal amount) {
        BigDecimal debtAmount = payerDebtOnPayee(payerId, payeeId);
        Map<String, BigDecimal> payeeDebts = balance.get(payeeId);
        // case 1: payer does not owe anything to payee
        if(debtAmount.compareTo(BigDecimal.valueOf(0)) == 0) {
            if(!payeeDebts.containsKey(payerId)) payeeDebts.put(payerId, amount);
            else payeeDebts.compute(payerId, (k, debt) -> debt.add(amount));
        }
        // case 2: payer owes some amount to payee
        else {
            Map<String, BigDecimal> payerDebts = balance.get(payerId);
            payerDebts.remove(payeeId);
            // if (debtAmount.compareTo(amount) == 0) already handled
            if (debtAmount.compareTo(amount) > 0) payerDebts.put(payeeId, debtAmount.subtract(amount));
            else if(debtAmount.compareTo(amount) < 0) {
                payeeDebts.remove(payerId);
                payerDebts.put(payerId, amount.subtract(debtAmount));
            }
        }
    }
    protected void settleDebt(String payerId, String payeeId, BigDecimal amount) {
        Map<String, BigDecimal> debts = balance.get(payerId);
        debts.computeIfPresent(payeeId, (k, debt) -> debt.subtract(amount));
        if (debts.get(payeeId).compareTo(BigDecimal.ZERO) == 0)
            debts.remove(payeeId);
    }
    protected void checkSettlementValidity(String payerId, String payeeId, BigDecimal amount) {
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
}
