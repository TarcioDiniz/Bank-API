package com.api.bank.BankApiLayer.Exception;

import com.api.bank.BankApiLayer.Business.ValidateCpfCnpj;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;
import com.api.bank.BankApiLayer.Services.TransactionAccountService;

import java.util.Objects;

public abstract class ExceptionCPF {

    public static void validateCPForCNJP(String cpf) {
        if (!new ValidateCpfCnpj(cpf).validate()){
            throw new RegisterAccountService.ExceptionRegister("CPF invalido");
        }
    }

    public static void validateCPFsMatch(String cpf1, String cpf2, String cpf3) {
        if (!(cpf1.equals(cpf2) && cpf2.equals(cpf3))) {
            throw new RegisterAccountService.ExceptionRegister("Os CPFs não coincidem");
        }
    }

    public static void validateCpfNotNull(String cpf) {
        if (Objects.equals(cpf, "") || cpf == null){
            throw new RegisterAccountService.ExceptionRegister("Já existe cadastro no sistema");
        }
    }
}
