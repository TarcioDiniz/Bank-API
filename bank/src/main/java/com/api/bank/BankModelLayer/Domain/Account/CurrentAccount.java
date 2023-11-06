package com.api.bank.BankModelLayer.Domain.Account;

import com.api.bank.BankDataLayer.MockData.SingletonController;
import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.Transactions;
import com.api.bank.BankModelLayer.Application.TypeTransactions;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Investment.Investment;
import com.api.bank.BankModelLayer.Infrastructure.Transactions.Deposit;

import java.math.BigDecimal;
import java.util.Date;
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
    public void setBalance(BigDecimal value) {
        balance.SetBalance(value);
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

    @Override
    public void deposit(int AGENCY, int ACCOUNT_ID, BigDecimal value, Date data, String description) {
        new Deposit().SetData(AGENCY, ACCOUNT_ID, value, data, description, TypeTransactions.DEPOSIT);
    }


}
