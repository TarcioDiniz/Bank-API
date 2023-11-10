package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Business.ValidateCpfCnpj;
import com.api.bank.BankApiLayer.Entity.Data.AccountBalanceData;
import com.api.bank.BankApiLayer.Entity.Data.AccountSecurityData;
import com.api.bank.BankApiLayer.Entity.Data.BankData;
import com.api.bank.BankApiLayer.Entity.Data.PersonalData;
import com.api.bank.BankApiLayer.Repository.AccountBalanceDataRepository;
import com.api.bank.BankApiLayer.Repository.AccountSecurityDataRepository;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Repository.PersonalDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.api.bank.BankApiLayer.Exception.ExceptionCPF.validateCPForCNJP;
import static com.api.bank.BankApiLayer.Exception.ExceptionCPF.validateCPFsMatch;

@Service
public class RegisterAccountService {
    private final AccountSecurityDataRepository accountSecurityDataRepository;
    private final BankDataRepository bankDataRepository;
    private final PersonalDataRepository personalDataRepository;
    private final AccountBalanceDataRepository accountBalanceDataRepository;

    public RegisterAccountService(
            AccountSecurityDataRepository accountSecurityDataRepository,
            BankDataRepository bankDataRepository,
            PersonalDataRepository personalDataRepository,
            AccountBalanceDataRepository accountBalanceDataRepository) {
        this.accountSecurityDataRepository = accountSecurityDataRepository;
        this.bankDataRepository = bankDataRepository;
        this.personalDataRepository = personalDataRepository;
        this.accountBalanceDataRepository = accountBalanceDataRepository;
    }

    @Transactional
    public void createAccount(BankData bankData, PersonalData personalData, AccountSecurityData accountSecurityData) {
        validateCPFsMatch(bankData.getCpf(), personalData.getCpf(), accountSecurityData.getCpf());
        validateCPForCNJP(bankData.getCpf());
        haveRegistration(bankData.getCpf());

        AccountBalanceData accountBalanceData = new AccountBalanceData();
        accountBalanceData.setCpf(bankData.getCpf());
        accountBalanceData.setCurrency("brl");
        accountBalanceData.setValue(BigDecimal.ZERO);


        accountSecurityDataRepository.save(accountSecurityData);
        bankDataRepository.save(bankData);
        personalDataRepository.save(personalData);
        accountBalanceDataRepository.save(accountBalanceData);
    }


    private void haveRegistration(String cpf) {
        if (personalDataRepository.findByCpf(cpf) != null) {
            throw new ExceptionRegister("Já existe cadastro no sistema");
        }
    }

    public static class ExceptionRegister extends RuntimeException {
        public ExceptionRegister(String message) {
            super(message);
        }
    }
}
