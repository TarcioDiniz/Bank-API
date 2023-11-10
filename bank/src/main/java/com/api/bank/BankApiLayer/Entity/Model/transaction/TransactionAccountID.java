package com.api.bank.BankApiLayer.Entity.Model.transaction;

import java.math.BigDecimal;

public class TransactionAccountID {
    private String thisCpf;
    private int agency;
    private int account_id;
    private String value;
    private String description;

    public String getThisCpf() {
        return thisCpf;
    }

    public void setThisCpf(String thisCpf) {
        this.thisCpf = thisCpf;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public BigDecimal getValue() {
        return new BigDecimal(value);
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
