package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.AccountSecurityData;
import com.api.bank.BankApiLayer.Repository.AccountSecurityDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountSecurityDataService {

    private final AccountSecurityDataRepository repository;

    private AccountSecurityDataService(AccountSecurityDataRepository repository) {
        this.repository = repository;
    }

    public List<AccountSecurityData> getAllAccountSecurityData() {
        return repository.findAll();
    }

    public AccountSecurityData getAccountSecurityDataByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public AccountSecurityData createAccountSecurityData(AccountSecurityData accountSecurityData) {
        return repository.save(accountSecurityData);
    }

    public AccountSecurityData updateAccountSecurityData(String cpf, AccountSecurityData accountSecurityData) {
        AccountSecurityData existingAccountSecurityData = repository.findByCpf(cpf);
        if (existingAccountSecurityData != null) {
            existingAccountSecurityData.setPassword(accountSecurityData.getPassword());
            return repository.save(existingAccountSecurityData);
        }
        return null;
    }

    public void deleteAccountSecurityData(String cpf) {
        repository.deleteByCpf(cpf);
    }


}
