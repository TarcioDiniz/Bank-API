package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
public class AccountBalanceData {
    @Id
    @Column(nullable = false, length = 11)
    private int cpf;
    @Column(nullable = false)
    private BigDecimal balance;
}
