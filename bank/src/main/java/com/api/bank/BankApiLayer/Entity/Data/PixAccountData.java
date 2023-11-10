package com.api.bank.BankApiLayer.Entity.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table
public class PixAccountData extends RepresentationModel<PixAccountData> {
    @Id
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 200)
    private String pixKey;

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
