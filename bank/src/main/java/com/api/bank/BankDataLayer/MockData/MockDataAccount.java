package com.api.bank.BankDataLayer.MockData;

import com.api.bank.BankModelLayer.Application.Account;
import com.api.bank.BankModelLayer.Application.TypeAccount;
import com.api.bank.BankModelLayer.Domain.Account.CurrentAccount;
import com.api.bank.BankModelLayer.Domain.Account.SavingsAccount;
import com.api.bank.BankModelLayer.Domain.Bank;
import com.api.bank.BankModelLayer.Domain.Login;
import com.api.bank.BankModelLayer.Infrastructure.DataBaseClient;
import com.api.bank.BankModelLayer.Infrastructure.Password;

import java.util.Date;

public class MockDataAccount {

    private static DataBaseClient getDataBaseClient(int CPF, String newOriginalPassword) {
        // Simulando Senha
        Password password = new Password();
        String originalPassword = "SecretPassword123";
        String encryptedPassword = password.encrypt(originalPassword);


        // Simulando DataBaseClient

        Date dateOfBirth = new Date(); // Substitua com a data de nascimento desejada
        DataBaseClient client = new DataBaseClient(1, "Clara", dateOfBirth, 123456789, "cliente@email.com", TypeAccount.CurrentAccount, encryptedPassword);


        if (CPF == client.getCPF() && newOriginalPassword.equals(originalPassword)) {
            return client;
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



    /*
    // simulando Bank
        Bank bank = new Bank(1, 1234, 56789);




        // simulando o Login

        Login login = new Login(CPF, newPassword);
    * */
}
