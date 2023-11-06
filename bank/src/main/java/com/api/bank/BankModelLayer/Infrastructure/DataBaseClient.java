package com.api.bank.BankModelLayer.Infrastructure;

import javax.xml.crypto.Data;

public class DataBaseClient {

    private String Name;
    private Data DateOfBirth;
    private int CPF;
    private String Email;


    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }

    public Data getDateOfBirth() {
        return DateOfBirth;
    }


    public void setDateOfBirth(Data dateOfBirth) {
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
}
