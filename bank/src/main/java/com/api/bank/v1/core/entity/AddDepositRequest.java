package com.api.bank.v1.core.entity;

import java.math.BigDecimal;

public class AddDepositRequest {

    private Long accountId;
    private BigDecimal amount;

    // Getters and setters

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
