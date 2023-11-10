package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Repository.AccountBalanceDataRepository;
import com.api.bank.BankApiLayer.Entity.Data.AccountBalanceData;
import com.api.bank.BankApiLayer.Entity.Model.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountBalanceDataService {

    private final AccountBalanceDataRepository repository;

    private AccountBalanceDataService(AccountBalanceDataRepository repository) {
        this.repository = repository;
    }

    public List<AccountBalanceData> getAllAccountBalanceData() {
        return repository.findAll();
    }

    public Balance getAccountBalanceDataByCpf(String cpf) {
         var balanceData = repository.findByCpf(cpf);
         return new Balance(balanceData.getValue(), balanceData.getCurrency());
    }

    public AccountBalanceData createAccountBalanceData(AccountBalanceData accountBalanceData) {
        return repository.save(accountBalanceData);
    }

    public AccountBalanceData updateAccountBalanceData(String cpf, AccountBalanceData updatedAccountBalanceData) {
        AccountBalanceData existingAccountBalanceData = repository.findByCpf(cpf);
        if (existingAccountBalanceData != null) {
            existingAccountBalanceData.setCurrency(updatedAccountBalanceData.getCurrency());
            existingAccountBalanceData.setValue(updatedAccountBalanceData.getValue());
            return repository.save(existingAccountBalanceData);
        }
        return null;
    }

    public void deleteAccountBalanceData(String cpf) {
        repository.deleteByCpf(cpf);
    }


}
