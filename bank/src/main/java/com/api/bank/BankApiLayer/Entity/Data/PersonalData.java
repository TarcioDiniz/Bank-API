package com.api.bank.BankApiLayer.Entity.Data;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Entity
@Table
public class PersonalData extends RepresentationModel<PersonalData> {
    @Id
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 200)
    private String full_name;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false)
    private String type_account;

    public void setCompletePersonalData(String cpf, String full_name, Date date, String email, String type_account) {
        this.cpf = cpf;
        this.full_name = full_name;
        this.date = date;
        this.email = email;
        this.type_account = type_account;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String name) {
        this.full_name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType_account() {
        return type_account;
    }

    public void setType_account(String type_account) {
        this.type_account = type_account;
    }
}
