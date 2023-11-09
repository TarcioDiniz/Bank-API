package com.api.bank.BankApiLayer.Enum;

public enum BankConst {
    AGENCY1(85646);

    private final int value;

    BankConst(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
