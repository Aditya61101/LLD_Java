package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private final Map<String, Map<String, BigDecimal>> balance = new HashMap<>();

    public BalanceStatus hasOutstandingBalance(String userId) {
        return BalanceStatus.CLEAR;
    }
    public void addDebt() {

    }
    public void settleDebt() {

    }
}
