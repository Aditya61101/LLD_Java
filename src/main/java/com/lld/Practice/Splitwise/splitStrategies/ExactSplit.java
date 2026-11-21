package com.lld.Practice.Splitwise.splitStrategies;

import com.lld.Practice.Splitwise.SplitStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExactSplit implements SplitStrategy {
    @Override
    public Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, Map<String, BigDecimal> allocations, int scale) {
        if (!validate(amount, allocations)) {
            throw new IllegalArgumentException("Invalid exact split");
        }
        return allocations;
    }

    private boolean validate(BigDecimal amount, Map<String, BigDecimal> allocations) {
        BigDecimal totalAllocation = BigDecimal.valueOf(0);
        for(BigDecimal allocation: allocations.values()) {
            if(allocation.signum() <= 0) return false;
            totalAllocation = totalAllocation.add(allocation);
        }
        return (amount.compareTo(totalAllocation) == 0);
    }
}
