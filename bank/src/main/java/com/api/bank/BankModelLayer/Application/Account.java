package com.api.bank.BankModelLayer.Application;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Investment.Investment;
import com.api.bank.BankModelLayer.Infrastructure.Transactions.Deposit;

import java.math.BigDecimal;
import java.util.List;

public interface Account {
    List<Transactions> getTransactions();
    BigDecimal getBalance();
    Investment setInvestment();
    DataBaseClient getMetaData();
    Boolean hasTransaction();
    Deposit deposit();
}
