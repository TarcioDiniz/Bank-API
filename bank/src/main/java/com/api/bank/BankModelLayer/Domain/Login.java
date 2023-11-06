package com.api.bank.BankModelLayer.Domain;

import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Password;

public class Login {
    private int ID;
    private DataBaseClient dataBaseClient;
    private Bank bank;
    private Password password;
}
