package com.lld.Practice.Splitwise;

import java.util.HashMap;
import java.util.Map;

public class SplitStrategyRegistry {
    Map<SplitType, SplitStrategy> splits = new HashMap<>();
    public void register(SplitType type, SplitStrategy split) {
        splits.put(type, split);
    }
    public SplitStrategy getSplit(SplitType type) {
        if(!splits.containsKey(type)) throw new IllegalArgumentException("Split strategy not available");
        return splits.get(type);
    }
}
