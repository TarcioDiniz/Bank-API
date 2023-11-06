package com.api.bank.BankModelLayer.Domain;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private int CPF;
    private int AGENCY;
    private int ACCOUNT_ID;

    public Bank(int CPF, int AGENCY, int ACCOUNT_ID) {
        this.CPF = CPF;
        this.AGENCY = AGENCY;
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    // CPF: {Agencia, conta}
    public static Map.Entry<Integer, Integer[]> generateBankAccount(int CPF){
        Random random = new Random();
        int agencia = random.nextInt(6001) + 2000; // Random number from 2000 to 8000
        int conta = random.nextInt(6001) + 2000; // Random number from 2000 to 8000

        Integer[] accountNumbers = {agencia, conta};

        return new AbstractMap.SimpleEntry<>(CPF, accountNumbers);
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public int getAGENCY() {
        return AGENCY;
    }

    public void setAGENCY(int AGENCY) {
        this.AGENCY = AGENCY;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }
}
