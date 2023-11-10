package com.api.bank.BankApiLayer.Entity.Data;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table
public class BankData extends RepresentationModel<BankData> {
    @Id
    @Column(nullable = false, length = 6)
    @GeneratedValue
    private int account;

    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 5)
    private int agency;


    public void setCompleteBankData(String cpf, int agency) {
        this.cpf = cpf;
        this.agency = agency;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public int getAccount() {
        return account;
    }

}
