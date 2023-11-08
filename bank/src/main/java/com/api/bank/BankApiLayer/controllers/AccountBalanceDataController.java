package com.api.bank.BankApiLayer.controllers;

import com.api.bank.BankApiLayer.model.AccountBalanceData;
import com.api.bank.BankApiLayer.model.BankData;
import com.api.bank.BankApiLayer.services.AccountBalanceDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class AccountBalanceDataController {

    private final AccountBalanceDataService service;

    public AccountBalanceDataController(AccountBalanceDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountBalanceData> getAllAccountBalanceData() {
        return service.getAllAccountBalanceData();
    }


    @GetMapping("/{cpf}")
    public AccountBalanceData getAccountBalanceDataByCpf(@PathVariable String cpf) {
        return service.getAccountBalanceDataByCpf(cpf);
    }

    @PostMapping
    public AccountBalanceData createAccountBalanceData(@RequestBody AccountBalanceData accountBalanceData) {
        return service.createAccountBalanceData(accountBalanceData);
    }

    @PutMapping("/{cpf}")
    public AccountBalanceData updateAccountBalanceData(@PathVariable String cpf, @RequestBody AccountBalanceData updatedAccountBalanceData) {
        return service.updateAccountBalanceData(cpf, updatedAccountBalanceData);
    }

    @DeleteMapping("/{cpf}")
    public void deleteAccountBalanceData(@PathVariable String cpf) {
        service.deleteAccountBalanceData(cpf);
    }


}
