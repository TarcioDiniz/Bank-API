package com.api.bank.BankApiLayer.exceptions;

public enum DefaultMessageErrorException {

    NUMERIC_VALUE("Please set a numeric value!");


    private final String message;

    DefaultMessageErrorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
