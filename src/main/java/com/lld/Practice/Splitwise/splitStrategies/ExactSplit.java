package com.lld.Practice.Splitwise.splitStrategies;

import com.lld.Practice.Splitwise.SplitStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public class ExactSplit implements SplitStrategy {
    private static final Currency CURRENCY = Currency.getInstance("INR");

    @Override
    public Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, Map<String, BigDecimal> allocations) {
        int scale = CURRENCY.getDefaultFractionDigits();
        if (!validate(amount, allocations, scale)) {
            throw new IllegalArgumentException("Invalid exact split");
        }
        return allocations;
    }

    private boolean validate(BigDecimal amount, Map<String, BigDecimal> allocations, int scale) {
        try {
            // setScale(2) means represent the number with two digits after the decimal point.
            // example 100->100.00, 100.2 -> 100.20, 100.055 -> raises exception
            amount.setScale(scale, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            return false;
        }
        BigDecimal totalAllocation = BigDecimal.valueOf(0);
        for(BigDecimal allocation: allocations.values()) {
            if(allocation.signum() <= 0) return false;
            totalAllocation = totalAllocation.add(allocation);
        }
        return (amount.compareTo(totalAllocation) == 0);
    }
}
