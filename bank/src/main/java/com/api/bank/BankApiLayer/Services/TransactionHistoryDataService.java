package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.PersonalData;
import com.api.bank.BankApiLayer.Entity.TransactionHistoryData;
import com.api.bank.BankApiLayer.Repository.TransactionHistoryDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryDataService {
    private final TransactionHistoryDataRepository repository;

    private TransactionHistoryDataService(TransactionHistoryDataRepository repository) {
        this.repository = repository;
    }

    public List<TransactionHistoryData> getAllTransactionHistoryData() {
        return repository.findAll();
    }

    public TransactionHistoryData getTransactionHistoryDataByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public TransactionHistoryData createTransactionHistoryData(TransactionHistoryData TransactionHistoryData) {
        return repository.save(TransactionHistoryData);
    }

    public TransactionHistoryData updateTransactionHistoryData(String cpf, TransactionHistoryData TransactionHistoryData) {
        TransactionHistoryData existingTransactionHistoryData = repository.findByCpf(cpf);
        if (existingTransactionHistoryData != null) {
            existingTransactionHistoryData.setType_transactions(TransactionHistoryData.getType_transactions());
            existingTransactionHistoryData.setDescription(TransactionHistoryData.getDescription());
            existingTransactionHistoryData.setTimestamp(TransactionHistoryData.getTimestamp());
            existingTransactionHistoryData.setValue(TransactionHistoryData.getValue());
            return repository.save(existingTransactionHistoryData);
        }
        return null;
    }

    public void deleteTransactionHistoryData(String cpf) {
        repository.deleteByCpf(cpf);
    }

}
