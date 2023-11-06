package com.api.bank.BankModelLayer;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Domain.Login;

/*
 * [x] -> Retornando o saldo (ainda falta mexer)
 * [] -> Deposito
 * [x] -> return MetaDados
 * [] -> Rendimento
 * */


public class Main {
    public static void main(String[] args) {
        int CPF = 123456789;
        //int CPF = 222222222;
        String password = "SecretPassword123";

        // login para saber sua conta
        Account account = new Login(CPF, password).getAccount();

        var metaData = account.getMetaData();
        System.out.println("Nome: " + metaData.getName());
        System.out.println("CPF: " + metaData.getCPF());
        System.out.println("Email: " + metaData.getEmail());
        System.out.println("Tipo da Conta: " + metaData.getTypeAccount());
        System.out.println("agencia: " + metaData.getBank().getAGENCY());
        System.out.println("conta: " + metaData.getBank().getACCOUNT_ID());
        System.out.println("Saldo: " + account.getBalance());

    }
}