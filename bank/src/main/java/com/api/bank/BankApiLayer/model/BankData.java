package com.api.bank.BankApiLayer.model;

import jakarta.persistence.*;

@Entity
@Table
public class BankData {
    @Id
    @Column(nullable = false, length = 11)
    private int cpf;
    @Column(nullable = false, length = 5)
    private int agency;
    @Column(nullable = false, length = 6)
    private int account_id;

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
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
}
