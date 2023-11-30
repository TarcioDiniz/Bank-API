package com.api.bank.v1.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class CreditCardStatement {

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "next_balance")
    private BigDecimal nextBalance;

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getNextBalance() {
        return nextBalance;
    }

    public void setNextBalance(BigDecimal nextBalance) {
        this.nextBalance = nextBalance;
    }

    // Getters and setters

    // Other constructors, methods, and nested classes

}
