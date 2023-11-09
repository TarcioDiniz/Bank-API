package com.api.bank.BankModelLayer;

import com.api.bank.BankApiLayer.Business.ValidateCpfCnpj;

/*
 * [x] -> Retornando o saldo (ainda falta mexer)
 * [] -> Deposito
 * [x] -> return MetaDados
 * [] -> Rendimento
 * */


public class Main {
    public static void main(String[] args) {
        String CPF = "08474356440";
        ValidateCpfCnpj validateCpfCnpj = new ValidateCpfCnpj(CPF);
        System.out.println(validateCpfCnpj.validate());

    }
}