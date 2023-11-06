package com.api.bank.BankModelLayer.Infrastructure;

import java.util.Date;

public class DataBaseClient {

        private String Name;
        private Date DateOfBirth;
        private int CPF;
        private String Email;

    public DataBaseClient(String Name, Date DateOfBirth, int CPF, String Email) {
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.CPF = CPF;
        this.Email = Email;
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
}
