package com.api.bank.BankModelLayer.Domain;

import com.api.bank.BankDataLayer.MockData.MockDataAccount;
import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Password;

public class Login {
    private int CPF;
    private String password;

    private Account account;

    public Login(int CPF, String password) {
        this.CPF = CPF;
        this.password = password;

        this.account = MockDataAccount.getAccount(CPF, password, this);

    }

    public Account getAccount() {
        return account;
    }

    public int getCPF() {
        return CPF;
    }

    public DataBaseClient getData(){
        return MockDataAccount.getDataCliente(this);
    }
}
