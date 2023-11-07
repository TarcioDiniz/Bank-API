package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class PersonalData {
    @Id
    @Column(nullable = false, length = 11)
    private String name;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false, length = 11)
    private int cpf;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false)
    private String type_account;


}
