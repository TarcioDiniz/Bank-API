package com.api.bank.BankDataLayer.MockData;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.TypeAccount;
import com.api.bank.BankModelLayer.Domain.Account.Balance;
import com.api.bank.BankModelLayer.Domain.Account.CurrentAccount;
import com.api.bank.BankModelLayer.Domain.Account.SavingsAccount;
import com.api.bank.BankModelLayer.Domain.Bank;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Password;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MockDataAccount {

    private static final Password password = new Password();

    private static List<DataBaseClient> bancoDeDados() {
        List<DataBaseClient> clienteArray = new ArrayList<>();

        // Simulando Senha
        String originalPassword = "SecretPassword123";
        String encryptedPassword = password.encrypt(originalPassword);

        // Simulando DataBaseClient 1
        Date dateOfBirth1 = new Date(); // Substitua com a data de nascimento desejada
        var CPF1 = 123456789;
        DataBaseClient client1 = new DataBaseClient(1, "Clara", dateOfBirth1, CPF1, "cliente1@email.com", TypeAccount.CurrentAccount, encryptedPassword, generateBank(CPF1));
        clienteArray.add(client1);

        return clienteArray;
    }

    private static Bank generateBank(int CPF) {
        var bank = Bank.generateBankAccount(CPF).getValue();
        var agencia = bank[0];
        int conta = bank[1];
        return new Bank(CPF, agencia, conta);
    }

    public static DataBaseClient getDataCliente(Login login) {

        var banco = bancoDeDados();

        for (int i = 0; i < banco.size(); i++) {
            if (banco.get(i).getCPF() == login.getCPF()) {
                return banco.get(i);
            }
        }
        return null;
    }

    private static DataBaseClient getDataBaseClient(int CPF, String newOriginalPassword) {

        var banco = bancoDeDados();

        for (int i = 0; i < banco.size(); i++) {
            if (CPF == banco.get(i).getCPF() && newOriginalPassword.equals(password.decrypt(banco.get(i).getPassword()))) {
                return banco.get(i);
            }
        }

        return null;
    }

    public static Account getAccount(int CPF, String newPassword, Login login) {
        DataBaseClient client = getDataBaseClient(CPF, newPassword);

        if ((client != null ? client.getTypeAccount() : null) == TypeAccount.CurrentAccount) {
            return new CurrentAccount(login);
        } else if ((client != null ? client.getTypeAccount() : null) == TypeAccount.SavingsAccount) {
            return new SavingsAccount(login);
        }
        return null;
    }

    public static void deposit(int AGENCY, int ACCOUNT_ID, BigDecimal value, Date data, String description) {
        var banco = bancoDeDados();

        var account = SingletonController.getInstance().getAccount();
        var agencia = account.getMetaData().getBank().getAGENCY();
        var conta = account.getMetaData().getBank().getACCOUNT_ID();
        account.setBalance(value);
        if (agencia == AGENCY && ACCOUNT_ID == conta) {
            System.out.println("aquiiii");

        }
    }





    /*
    // simulando Bank
        Bank bank = new Bank(1, 1234, 56789);




        // simulando o Login

        Login login = new Login(CPF, newPassword);
    * */
}
