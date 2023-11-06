package com.api.bank.BankModelLayer.Domain.Account;

import java.math.BigDecimal;

public class Balance {

    private int CPF;
    private BigDecimal balance = BigDecimal.ZERO;

    public Balance(int newCPF, BigDecimal newValue) {
        this.CPF = newCPF;
        SetBalance(newValue);
    }

    public void SetBalance(BigDecimal newValue) {
        this.balance = this.balance.add(newValue);
    }


    public BigDecimal getBalance() {
        return balance;
    }
}