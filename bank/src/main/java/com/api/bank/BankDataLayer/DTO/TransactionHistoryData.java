package com.api.bank.BankDataLayer.DTO;

import java.sql.Timestamp;

public class TransactionHistoryData {
    private int id;
    private int cpf;
    private String value;
    private Timestamp timestamp;
    private String description;
    private String type_transactions;

}
