package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.PersonalData;
import com.api.bank.BankApiLayer.Repository.PersonalDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonalDataService {

    private final PersonalDataRepository repository;

    private PersonalDataService(PersonalDataRepository repository) {
        this.repository = repository;
    }

    public List<PersonalData> getAllPersonalData() {
        return repository.findAll();
    }

    public PersonalData getPersonalDataByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public PersonalData createPersonalData(PersonalData PersonalData) {
        return repository.save(PersonalData);
    }

    public PersonalData updatePersonalData(String cpf, PersonalData PersonalData) {
        PersonalData existingPersonalData = repository.findByCpf(cpf);
        if (existingPersonalData != null) {
            existingPersonalData.setDate(PersonalData.getDate());
            existingPersonalData.setEmail(PersonalData.getEmail());
            existingPersonalData.setName(PersonalData.getName());
            existingPersonalData.setType_account(PersonalData.getType_account());
            return repository.save(existingPersonalData);
        }
        return null;
    }

    public void deletePersonalData(String cpf) {
        repository.deleteByCpf(cpf);
    }

}
