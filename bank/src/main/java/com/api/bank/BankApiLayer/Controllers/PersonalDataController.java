package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.PersonalData;
import com.api.bank.BankApiLayer.Services.PersonalDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalDataController {

    private final PersonalDataService service;

    public PersonalDataController(PersonalDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonalData> getAllPersonalData() {
        return service.getAllPersonalData();
    }


    @GetMapping("/{cpf}")
    public PersonalData getPersonalDataByCpf(@PathVariable String cpf) {
        return service.getPersonalDataByCpf(cpf);
    }

    @PostMapping
    public PersonalData createPersonalData(@RequestBody PersonalData PersonalData) {
        return service.createPersonalData(PersonalData);
    }

    @PutMapping("/{cpf}")
    public PersonalData updatePersonalData(@PathVariable String cpf, @RequestBody PersonalData updatedPersonalData) {
        return service.updatePersonalData(cpf, updatedPersonalData);
    }

    @DeleteMapping("/{cpf}")
    public void deletePersonalData(@PathVariable String cpf) {
        service.deletePersonalData(cpf);
    }

}
