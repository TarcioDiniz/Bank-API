package com.api.bank.BankModelLayer.Application;

import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Investment.Investment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface Account {
    List<Transactions> getTransactions();

    BigDecimal getBalance();
    void setBalance(BigDecimal value);

    Investment setInvestment();

    DataBaseClient getMetaData();

    Boolean hasTransaction();

    void deposit(int AGENCY, int ACCOUNT_ID, BigDecimal value, Date data, String description);

}
