package com.api.bank.BankApiLayer.Enum;

public enum TypeTransactions {
    DEPOSIT("DEPOSIT"),
    INCOME("INCOME"),
    TRANSFER("TRANSFER"),
    WITHDRAW("WITHDRAW");

    private final String value;

    TypeTransactions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
