package com.api.bank.BankApiLayer.services;

import com.api.bank.BankApiLayer.model.BankData;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDataService {

    private final BankDataRepository repository;

    public BankDataService(BankDataRepository repository) {
        this.repository = repository;
    }

    public List<BankData> getAllBankData() {
        List<BankData> allBankData = repository.findAll();
        for (int i = 0; i < allBankData.size(); i++) {
            allBankData.get(i).setCpf(maskCpf(allBankData.get(i).getCpf()));
        }
        return allBankData;
    }

    private String maskCpf(String cpf) {
        return cpf.replaceAll("\\d(?=\\d{4})", "*");
    }


    public BankData getBankDataByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public BankData createBankData(BankData bankData) {
        return repository.save(bankData);
    }

    public BankData updateBankData(String cpf, BankData updatedBankData) {
        BankData existingBankData = repository.findByCpf(cpf);
        if (existingBankData != null) {
            // Atualize os campos do existingBankData com os valores do updatedBankData
            existingBankData.setAgency(updatedBankData.getAgency());
            existingBankData.setAccount_id(updatedBankData.getAccount_id());
            return repository.save(existingBankData);
        }
        return null;
    }

    public void deleteBankData(String cpf) {
        repository.deleteByCpf(cpf);
    }
}
