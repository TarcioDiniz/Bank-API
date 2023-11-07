package com.api.bank.BankApiLayer.services;

import com.api.bank.BankApiLayer.model.BankData;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankDataRepository repository;

    public List<BankData> getAllBankData() {
        return repository.findAll();
    }

    public BankData getBankDataByCpf(int cpf) {
        return repository.findByCpf(cpf);
    }

    public BankData createBankData(BankData bankData) {
        return repository.save(bankData);
    }

    public BankData updateBankData(int cpf, BankData updatedBankData) {
        BankData existingBankData = repository.findByCpf(cpf);
        if (existingBankData != null) {
            // Atualize os campos do existingBankData com os valores do updatedBankData
            existingBankData.setAgency(updatedBankData.getAgency());
            existingBankData.setAccount_id(updatedBankData.getAccount_id());
            return repository.save(existingBankData);
        }
        return null;
    }

    public void deleteBankData(int cpf) {
        repository.deleteByCpf(cpf);
    }
}
