package com.api.bank.BankModelLayer.Domain.Account;

import java.math.BigDecimal;

public class Balance {

    private int ID;
    private BigDecimal balance;

    public Balance(int newID, BigDecimal newValue) {
        this.ID = newID;
        SetBalance(newValue);
    }

    private void SetBalance(BigDecimal newValue) {
        // TODO implements context
        this.balance = newValue;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}