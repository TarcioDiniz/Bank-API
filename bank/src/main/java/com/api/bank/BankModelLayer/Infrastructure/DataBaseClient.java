package com.api.bank.BankModelLayer.Infrastructure;

import com.api.bank.BankModelLayer.Application.TypeAccount;
import com.api.bank.BankModelLayer.Domain.Bank;

import java.util.Date;

public class DataBaseClient {

    private int ID;
    private String Name;
    private Date DateOfBirth;
    private int CPF;
    private String Email;
    private TypeAccount typeAccount;
    private String password;
    private Bank bank;

    public DataBaseClient(int ID,
                          String Name,
                          Date DateOfBirth,
                          int CPF,
                          String Email,
                          TypeAccount typeAccount,
                          String password,
                          Bank bank) {
        this.ID = ID;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.CPF = CPF;
        this.Email = Email;
        this.typeAccount = typeAccount;
        this.password = password;
        this.bank = bank;
    }


    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }


    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }


    public int getCPF() {
        return CPF;
    }


    public void setCPF(int CPF) {
        this.CPF = CPF;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }
    public Bank getBank(){
        return bank;
    }
}
