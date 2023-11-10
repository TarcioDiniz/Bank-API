package com.api.bank.BankApiLayer.Entity.Model;

import java.math.BigDecimal;

public class Transaction {
    private String cpf;
    private String value;
    private String description;

    private Transaction(String cpf, String value, String description) {
        setCpf(cpf);
        setValue(value);
        setDescription(description);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValue() {
        return new BigDecimal(this.value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
