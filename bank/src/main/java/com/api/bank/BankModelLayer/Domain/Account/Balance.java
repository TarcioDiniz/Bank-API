package com.api.bank.BankModelLayer.Domain.Account;

import java.math.BigDecimal;

public class Balance {

    private int CPF;
    private BigDecimal balance;

    public Balance(int newCPF, BigDecimal newValue) {
        this.CPF = newCPF;
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