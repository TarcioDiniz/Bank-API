package com.api.bank.BankModelLayer.Infrastructure.Transactions;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.Transactions;
import com.api.bank.BankModelLayer.Application.TypeTransactions;
import com.api.bank.BankModelLayer.Infrastructure.TransactionsData;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public class Transfer implements Transactions {
    @Override
    public void SetData(int ID, Account account, BigDecimal value, Data data, String description, TypeTransactions type) {

    }

    @Override
    public TransactionsData getBankStatement() {
        return null;
    }
}
