package com.api.bank.BankModelLayer.Infrastructure.Transactions;

import com.api.bank.BankDataLayer.MockData.MockDataAccount;
import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.Transactions;
import com.api.bank.BankModelLayer.Application.TypeTransactions;
import com.api.bank.BankModelLayer.Domain.Account.Balance;
import com.api.bank.BankModelLayer.Infrastructure.TransactionsData;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit implements Transactions {
    @Override
    public void SetData(Balance balance, BigDecimal value, Date data, String description, TypeTransactions type) {
        if (type.equals(TypeTransactions.DEPOSIT)) {
            MockDataAccount.deposit(balance, value, data, description);
        }
    }

    @Override
    public TransactionsData getBankStatement() {
        return null;
    }
}
