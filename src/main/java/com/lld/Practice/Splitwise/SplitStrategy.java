package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, Map<String, BigDecimal> allocations, int scale);
}
