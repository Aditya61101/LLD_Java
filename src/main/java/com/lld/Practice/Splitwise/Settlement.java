package com.lld.Practice.Splitwise;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Settlement {
    private final String id;
    private final String payerId;
    private final String payeeId;
    private final BigDecimal amount;
    private final Currency currency;

    public Settlement(String payerId, String payeeId, BigDecimal amount, Currency currency) {
        this.id = "SE-"+ UUID.randomUUID();
        this.payeeId = payeeId;
        this.payerId = payerId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getPayerId() {
        return payerId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public Currency getCurrency() { return currency; }
}
