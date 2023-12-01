package com.api.bank.v1.core.entity;

import java.math.BigDecimal;

public class TransactionUpdateRequest {
    private String name;
    private String category;
    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    // Getters and setters
}