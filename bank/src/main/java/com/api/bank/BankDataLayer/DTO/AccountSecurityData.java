package com.api.bank.BankDataLayer.DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "account_security_data")
public class AccountSecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 11)
    private int cpf;

    @Column(nullable = false, length = 100)
    private String password;
}
