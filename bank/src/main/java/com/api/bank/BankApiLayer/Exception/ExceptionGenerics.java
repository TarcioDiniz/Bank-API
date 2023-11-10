package com.api.bank.BankApiLayer.Exception;

import com.api.bank.BankApiLayer.Business.ValidateCpfCnpj;
import com.api.bank.BankApiLayer.Repository.AccountBalanceDataRepository;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Repository.PixAccountDataRepository;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class ExceptionGenerics {

    public static void validateCPForCNJP(String cpf) {
        if (!new ValidateCpfCnpj(cpf).validate()) {
            throw new RegisterAccountService.ExceptionRegister("CPF invalido");
        }
    }

    public static void validateCPFsMatch(String cpf1, String cpf2, String cpf3) {
        if (!(cpf1.equals(cpf2) && cpf2.equals(cpf3))) {
            throw new RegisterAccountService.ExceptionRegister("Os CPFs não coincidem");
        }
    }

    public static void validateCPFNotNull(String cpf) {
        if (Objects.equals(cpf, "") || cpf == null) {
            throw new RegisterAccountService.ExceptionRegister("Já existe cadastro no sistema");
        }
    }

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
                    "Não foi possível realizar a operação. Saldo insuficiente!\n" +
                            "Valor Solicitado: R$ " + value + ".\n" +
                            "Seu Saldo: R$ " + balanceInTheAccount + ".");
        }
    }

    public static void validatePixKey(String pixKey, PixAccountDataRepository pixAccountDataRepository) {
        if (pixAccountDataRepository.findByPixKey(pixKey) == null) {
            throw new RegisterAccountService.ExceptionRegister("Chave Pix Inexistente");
        }
    }

    public static void validadeBankDataAccount(int account_id, BankDataRepository bankDataRepository) {
        if (bankDataRepository.findByAccount(account_id) == null) {
            throw new RegisterAccountService.ExceptionRegister("Conta Inexistente");
        }
    }

}
