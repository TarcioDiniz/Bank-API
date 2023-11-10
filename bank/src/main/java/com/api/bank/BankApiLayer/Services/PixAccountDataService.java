package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.Data.PixAccountData;
import com.api.bank.BankApiLayer.Entity.Model.PixAccount;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Repository.PixAccountDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.bank.BankApiLayer.Exception.ExceptionGenerics.*;

@Service
public class PixAccountDataService {
    private final PixAccountDataRepository pixAccountDataRepository;

    private final BankDataRepository bankDataRepository;

    private PixAccountDataService(PixAccountDataRepository repository, BankDataRepository bankDataRepository
    ) {
        this.pixAccountDataRepository = repository;
        this.bankDataRepository = bankDataRepository;
    }


    public void createKeyPix(PixAccount pixAccount) {
        validateCPFNotNull(pixAccount.getCpf());
        validateCPForCNJP(pixAccount.getCpf());
        validateAccount(pixAccount.getCpf(), bankDataRepository);

        PixAccountData pixAccountData = new PixAccountData();
        pixAccountData.setCpf(pixAccount.getCpf());
        pixAccountData.setPixKey(pixAccount.getPixKey());

        pixAccountDataRepository.save(pixAccountData);

    }


    public List<PixAccountData> getAllPixAccountData() {
        return pixAccountDataRepository.findAll();
    }

    public PixAccount getPixAccountDataByCpf(String cpf) {
        var account = pixAccountDataRepository.findByCpf(cpf);
        PixAccount pixAccount = new PixAccount();
        pixAccount.setCpf(account.getCpf());
        pixAccount.setPixKey(account.getPixKey());


        return pixAccount;
    }

    public PixAccountData updatePixAccountData(String cpf, PixAccountData pixAccountData) {
        PixAccountData existingPixAccountData = pixAccountDataRepository.findByCpf(cpf);
        if (existingPixAccountData != null) {
            existingPixAccountData.setPixKey(pixAccountData.getPixKey());
            return pixAccountDataRepository.save(existingPixAccountData);
        }
        return null;
    }

    public void deletePixAccountData(String cpf) {
        pixAccountDataRepository.deleteByCpf(cpf);
    }
}
