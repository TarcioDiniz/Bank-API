package com.api.bank.BankModelLayer.Domain.Account;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Investment.Investment;
import com.api.bank.BankModelLayer.Application.Transactions;

import java.math.BigDecimal;
import java.util.List;

public class SavingsAccount implements Account {
    @Override
    public List<Transactions> getTransactions() {
        return null;
    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }

    @Override
    public Investment setInvestment() {
        return null;
    }

    @Override
    public DataBaseClient getMetaData() {
        return null;
    }

    private void SetInvestment(){
        // TODO implement context
    }
}
