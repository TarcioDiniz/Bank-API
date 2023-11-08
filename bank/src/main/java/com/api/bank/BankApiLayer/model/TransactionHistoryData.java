package com.api.bank.BankApiLayer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table
public class TransactionHistoryData {
    @Id
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private Timestamp timestamp;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private String type_transactions;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_transactions() {
        return type_transactions;
    }

    public void setType_transactions(String type_transactions) {
        this.type_transactions = type_transactions;
    }
}
