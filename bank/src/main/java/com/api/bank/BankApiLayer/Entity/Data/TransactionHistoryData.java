package com.api.bank.BankApiLayer.Entity.Data;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

@Entity
@Table
public class TransactionHistoryData extends RepresentationModel<TransactionHistoryData> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11)
    private Long id;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private long timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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
