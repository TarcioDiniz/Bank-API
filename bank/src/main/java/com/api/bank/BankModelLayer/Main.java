package com.api.bank.BankModelLayer;

import com.api.bank.BankDataLayer.MockData.SingletonController;
import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.Transactions.Deposit;

import java.math.BigDecimal;
import java.util.Date;

/*
 * [x] -> Retornando o saldo (ainda falta mexer)
 * [] -> Deposito
 * [x] -> return MetaDados
 * [] -> Rendimento
 * */


public class Main {
    public static void main(String[] args) {
        int CPF = 123456789;
        //int CPF2 = 222222222;
        String password = "SecretPassword123";

        // login para saber sua conta
        Account account = new Login(CPF, password).getAccount();
        SingletonController.getInstance().SetAccount(account);

        var metaData = account.getMetaData();
        System.out.println("Nome: " + metaData.getName());
        System.out.println("CPF: " + metaData.getCPF());
        System.out.println("Email: " + metaData.getEmail());
        System.out.println("Tipo da Conta: " + metaData.getTypeAccount());
        System.out.println("agencia: " + metaData.getBank().getAGENCY());
        System.out.println("conta: " + metaData.getBank().getACCOUNT_ID());
        System.out.println("Saldo: " + account.getBalance());
        System.out.println();

        BigDecimal valor = new BigDecimal("13.22");
        System.out.println("Deposito de: " + valor.toString());
        var agencia= 3737;
        var conta= 4376;
        account.deposit(agencia, conta, valor, new Date(), "");

        System.out.println();

        System.out.println("Nome: " + metaData.getName());
        System.out.println("CPF: " + metaData.getCPF());
        System.out.println("Email: " + metaData.getEmail());
        System.out.println("Tipo da Conta: " + metaData.getTypeAccount());
        System.out.println("agencia: " + metaData.getBank().getAGENCY());
        System.out.println("conta: " + metaData.getBank().getACCOUNT_ID());
        System.out.println("Saldo: " + account.getBalance());

    }
}