package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class TransactionHistoryData {
    @Id
    @Column(nullable = false, length = 11)
    private int cpf;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private Timestamp timestamp;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private String type_transactions;

}
