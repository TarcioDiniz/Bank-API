package com.api.bank.BankDataLayer.MockData;

import com.api.bank.BankModelLayer.Application.Account;

public class SingletonController {
    private static SingletonController instance;

    private Account account;
    private SingletonController() {
        // Inicialize o controlador, se necessário
    }

    public static SingletonController getInstance() {
        if (instance == null) {
            synchronized (SingletonController.class) {
                if (instance == null) {
                    instance = new SingletonController();
                }
            }
        }
        return instance;
    }

    public void SetAccount(Account account){
        this.account = account;
    }

    public Account getAccount(){
        return account;
    }
}
