package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Entity.Data.BankData;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDataService {

    private final BankDataRepository repository;

    public BankDataService(BankDataRepository repository) {
        this.repository = repository;
    }

    public CollectionModel<BankData> getAllBankData() {
        List<BankData> allBankData = repository.findAll();
        for (BankData bankData : allBankData) {
            bankData.setCpf(maskCpf(bankData.getCpf()));

            // Add self-link for each entity
            Link selfLink = Link.of("/bank/" + bankData.getCpf());
            bankData.add(selfLink);
        }


        return CollectionModel.of(allBankData);
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
            return repository.save(existingBankData);
        }
        return null;
    }

    public void deleteBankData(String cpf) {
        repository.deleteByCpf(cpf);
    }
}
