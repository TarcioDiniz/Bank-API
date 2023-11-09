package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Business.ValidateCpfCnpj;
import com.api.bank.BankApiLayer.Entity.Data.AccountSecurityData;
import com.api.bank.BankApiLayer.Entity.Data.BankData;
import com.api.bank.BankApiLayer.Entity.Data.PersonalData;
import com.api.bank.BankApiLayer.Repository.AccountSecurityDataRepository;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Repository.PersonalDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAccountService {
    private final AccountSecurityDataRepository accountSecurityDataRepository;
    private final BankDataRepository bankDataRepository;
    private final PersonalDataRepository personalDataRepository;

    public RegisterAccountService(
            AccountSecurityDataRepository accountSecurityDataRepository,
            BankDataRepository bankDataRepository,
            PersonalDataRepository personalDataRepository) {
        this.accountSecurityDataRepository = accountSecurityDataRepository;
        this.bankDataRepository = bankDataRepository;
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    public void createAccount(BankData bankData, PersonalData personalData, AccountSecurityData accountSecurityData) {
        validateCPFsMatch(bankData.getCpf(), personalData.getCpf(), accountSecurityData.getCpf());
        validateCPForCNJP(bankData.getCpf());
        haveRegistration(bankData.getCpf());

        accountSecurityDataRepository.save(accountSecurityData);
        bankDataRepository.save(bankData);
        personalDataRepository.save(personalData);
    }

    private void validateCPFsMatch(String cpf1, String cpf2, String cpf3) {
        if (!(cpf1.equals(cpf2) && cpf2.equals(cpf3))) {
            throw new ExceptionRegister("Os CPFs não coincidem");
        }
    }

    private void haveRegistration(String cpf) {
        if (personalDataRepository.findByCpf(cpf) != null) {
            throw new ExceptionRegister("Já existe cadastro no sistema");
        }
    }

    private void validateCPForCNJP(String cpf) {
        if (!new ValidateCpfCnpj(cpf).validate()){
            throw new ExceptionRegister("CPF invalido");
        }
    }

    public static class ExceptionRegister extends RuntimeException {
        public ExceptionRegister(String message) {
            super(message);
        }
    }
}
