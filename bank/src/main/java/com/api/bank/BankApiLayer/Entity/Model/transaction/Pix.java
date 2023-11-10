package com.api.bank.BankApiLayer.Entity.Model.transaction;

import java.math.BigDecimal;

public class Pix {
    private String thisCpf;
    private String pixKey;
    private String value;
    private String description;

    public String getThisCpf() {
        return thisCpf;
    }

    public void setThisCpf(String thisCpf) {
        this.thisCpf = thisCpf;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
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
