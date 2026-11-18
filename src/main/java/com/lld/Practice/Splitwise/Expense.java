package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class Expense {
    private final String id;
    private final String payerId;
    private final BigDecimal amount;
    Map<String, BigDecimal> shares;
    private final SplitType splitType;
    private String description;

    public Expense(String payerId, String description, BigDecimal amount, SplitType splitType, Map<String, BigDecimal> shares) {
        this.id = "EX-"+ UUID.randomUUID();
        this.payerId = payerId;
        this.splitType = splitType;
        this.amount = amount;
        this.description = description;
        this.shares = shares;
    }

    public void renameDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getPayerId() {
        return payerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public String getId() {
        return id;
    }
    public Map<String, BigDecimal> getShares() {
        return shares;
    }
}
