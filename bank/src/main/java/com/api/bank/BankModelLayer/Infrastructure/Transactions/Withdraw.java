package com.api.bank.BankModelLayer.Infrastructure.Transactions;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.Transactions;
import com.api.bank.BankModelLayer.Application.TypeTransactions;
import com.api.bank.BankModelLayer.Domain.Account.Balance;
import com.api.bank.BankModelLayer.Infrastructure.TransactionsData;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

public class Withdraw implements Transactions {

    @Override
    public void SetData(Balance balance, BigDecimal value, Date data, String description, TypeTransactions type) {

    }

    @Override
    public TransactionsData getBankStatement() {
        return null;
    }
}
