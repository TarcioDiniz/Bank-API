package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

@Entity
@Table
public class AccountSecurityData {
    @Id
    @Column(nullable = false, length = 11)
    private int cpf;

    @Column(nullable = false, length = 100)
    private String password;
}
