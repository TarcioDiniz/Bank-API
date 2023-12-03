package com.api.bank.v1.core.entity;

import java.math.BigDecimal;

public class BalanceRequest {
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
