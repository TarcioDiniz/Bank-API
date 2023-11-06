package com.api.bank.BankModelLayer.Domain;

import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Password;

public class Login {
    private int ID;
    private DataBaseClient dataBaseClient;
    private Bank bank;
    private String password;

    public Login(int ID, DataBaseClient dataBaseClient, Bank bank, String password) {
        this.ID = ID;
        this.dataBaseClient = dataBaseClient;
        this.bank = bank;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

}
