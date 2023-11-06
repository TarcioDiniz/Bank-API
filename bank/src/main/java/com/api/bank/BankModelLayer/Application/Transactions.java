package com.api.bank.BankModelLayer.Application;

import com.api.bank.BankModelLayer.Infrastructure.TransactionsData;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

public interface Transactions {


    void SetData(int AGENCY, int ACCOUNT_ID, BigDecimal value, Date data, String description, TypeTransactions type);

    TransactionsData getBankStatement();
}
