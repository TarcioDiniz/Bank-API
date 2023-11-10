package com.api.bank.BankApiLayer.Entity.Model;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;
    private String currency;

    public Balance(BigDecimal balance,String currency){
        this.balance = balance;
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
