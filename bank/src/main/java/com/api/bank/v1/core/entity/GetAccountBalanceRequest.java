package com.api.bank.v1.core.entity;

import java.math.BigDecimal;

public class GetAccountBalanceRequest {
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

