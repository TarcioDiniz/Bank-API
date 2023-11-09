package com.api.bank.BankApiLayer.Entity.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Entity
@Table
public class AccountBalanceData extends RepresentationModel<AccountBalanceData> {
    @Id
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 3)
    private String currency;

    public String getCpf() {
        return cpf;
    }

    @Column(nullable = false)
    private BigDecimal value;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal balance) {
        this.value = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
