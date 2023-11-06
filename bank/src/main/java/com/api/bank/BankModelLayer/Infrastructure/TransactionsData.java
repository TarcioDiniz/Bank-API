package com.api.bank.BankModelLayer.Infrastructure;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.TypeTransactions;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public class TransactionsData {

    int ID;
    Account account;
    BigDecimal value;
    Data data;
    String description;
    TypeTransactions type;

}
