package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
public class AccountBalanceData {
    @Id
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false)
    private BigDecimal balance;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
