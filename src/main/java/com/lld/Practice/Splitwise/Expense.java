package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;

public class Expense {
    private final String id;
    private final String payerId;
    private final BigDecimal amount;
    private final Map<String, BigDecimal> shares;
    private final SplitType splitType;
    private final Currency currency;
    private String description;

    public Expense(String payerId, String description, BigDecimal amount, SplitType splitType, Map<String, BigDecimal> shares, Currency currency) {
        this.id = "EX-"+ UUID.randomUUID();
        this.payerId = payerId;
        this.splitType = splitType;
        this.amount = amount;
        this.description = description;
        // Map.copyOf creates an unmodifiable snapshot of the original map
        this.shares = Map.copyOf(shares);
        this.currency = currency;
    }

    public void renameDescription(String newDescription) {
        if(newDescription == null || newDescription.isBlank()) throw new IllegalArgumentException("Empty description not allowed.");
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

    public Currency getCurrency() { return currency; }
}
