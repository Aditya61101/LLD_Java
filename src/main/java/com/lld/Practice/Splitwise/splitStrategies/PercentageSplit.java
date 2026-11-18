package com.lld.Practice.Splitwise.splitStrategies;

import com.lld.Practice.Splitwise.SplitStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PercentageSplit implements SplitStrategy {
    @Override
    public Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, List<BigDecimal> contributions) {
        return Map.of();
    }

    @Override
    public boolean validate(BigDecimal amount, List<BigDecimal> contributions) {
        return false;
    }
}
