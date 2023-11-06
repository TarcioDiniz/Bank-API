package com.api.bank.BankModelLayer.Domain.Account;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.Transactions;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Investment.Investment;

import java.math.BigDecimal;
import java.util.List;

public class CurrentAccount implements Account {

    private final Balance balance;
    private Login login;

    public CurrentAccount(Login login) {

        if (!hasTransaction()) {
            this.balance = new Balance(login.getCPF(), new BigDecimal("2000"));
        } else {
            // Balance nao pode inicar com Zero, faça uma busca no banco de dados primeiro
            this.balance = new Balance(login.getCPF(), BigDecimal.ZERO);
        }
        this.login = login;
    }

    @Override
    public List<Transactions> getTransactions() {
        return null;
    }

    @Override
    public BigDecimal getBalance() {
        return balance.getBalance();
    }

    @Override
    public Investment setInvestment() {
        return null;
    }

    @Override
    public DataBaseClient getMetaData() {
        return login.getData();
    }

    @Override
    public Boolean hasTransaction() {
        // faça a busca no banco de dados para saber se tem ou nao transaçoes
        return false;
    }
}
