package com.api.bank.BankModelLayer.Application;

import com.api.bank.BankModelLayer.Infrastructure.TransactionsData;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public interface Transactions {

    void SetData(
      int ID,
      Account account,
      BigDecimal value,
      Data data,
      String description,
      TypeTransactions type
    );

    TransactionsData getBankStatement();
}
