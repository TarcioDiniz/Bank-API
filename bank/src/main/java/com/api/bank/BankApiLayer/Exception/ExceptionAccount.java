package com.api.bank.BankApiLayer.Exception;

import com.api.bank.BankApiLayer.Repository.AccountBalanceDataRepository;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;

import java.math.BigDecimal;

public class ExceptionAccount {

    public static void validateAccount(String cpf, BankDataRepository bankDataRepository) {
        if (bankDataRepository.findByCpf(cpf) == null) {
            throw new RegisterAccountService.ExceptionRegister("Conta Inexistente");
        }
    }

    public static void validateAccountValueBalance(
            String cpf,
            BigDecimal value,
            AccountBalanceDataRepository accountBalanceDataRepository) {
        BigDecimal balanceInTheAccount = accountBalanceDataRepository.findByCpf(cpf).getValue();
        if (value.compareTo(balanceInTheAccount) > 0) {
            throw new RegisterAccountService.ExceptionRegister(
                    "Não foi possível realizar o saque. Saldo insuficiente!\n"+
                    "Valor Solicitado: R$ "+ value+".\n"+
                    "Seu Saldo: R$ "+balanceInTheAccount+".");
        }
    }

}
