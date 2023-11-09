package com.api.bank.BankApiLayer.Entity.Model;

import java.util.Date;

public class RegisterAccount {
    private String cpf;
    private String name;
    private Date date;
    private String email;
    private String typeAccount;
    private String password;

    public RegisterAccount(String cpf, String name, Date date, String email, String typeAccount, String password) {
        this.cpf = cpf;
        this.name = name;
        this.date = date;
        this.email = email;
        this.typeAccount = typeAccount;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public String getPassword() {
        return password;
    }
}
