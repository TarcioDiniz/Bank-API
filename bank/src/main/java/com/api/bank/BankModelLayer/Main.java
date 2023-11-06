package com.api.bank.BankModelLayer;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Domain.Account.CurrentAccount;
import com.api.bank.BankModelLayer.Domain.Account.SavingsAccount;

/*
* [] -> Retornando o saldo
* [] -> Transferência
* []
* []
* */


public class Main {
    public static void main(String[] args) {
        Account contaCorrente = new CurrentAccount();
        Account contaPoupanca = new SavingsAccount();

        System.out.println(contaCorrente.getBalance());

    }
}