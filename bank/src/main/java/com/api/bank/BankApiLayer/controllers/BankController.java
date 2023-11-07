package com.api.bank.BankApiLayer.controllers;

import com.api.bank.BankApiLayer.model.BankData;
import com.api.bank.BankApiLayer.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService service;

    @GetMapping
    public List<BankData> getAllBankData() {
        return service.getAllBankData();
    }

    @GetMapping("/{cpf}")
    public BankData getBankDataByCpf(@PathVariable int cpf) {
        return service.getBankDataByCpf(cpf);
    }

    @PostMapping
    public BankData createBankData(@RequestBody BankData bankData) {
        return service.createBankData(bankData);
    }

    @PutMapping("/{cpf}")
    public BankData updateBankData(@PathVariable int cpf, @RequestBody BankData updatedBankData) {
        return service.updateBankData(cpf, updatedBankData);
    }

    @DeleteMapping("/{cpf}")
    public void deleteBankData(@PathVariable int cpf) {
        service.deleteBankData(cpf);
    }
}
