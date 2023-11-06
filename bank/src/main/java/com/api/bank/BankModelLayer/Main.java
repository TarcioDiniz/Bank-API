package com.api.bank.BankModelLayer;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Domain.Account.CurrentAccount;
import com.api.bank.BankModelLayer.Domain.Account.SavingsAccount;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.Password;

/*
 * [] -> Retornando o saldo
 * [] -> Transferência
 * []
 * []
 * */


public class Main {
    public static void main(String[] args) {
        int CPF = 123456789;
        String password = "SecretPassword123";


        Account account = new Login(CPF, password).getAccount();

        System.out.println(account.getBalance());

    }
}