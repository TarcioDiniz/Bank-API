package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.AccountSecurityData;
import com.api.bank.BankApiLayer.Services.AccountSecurityDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/security")
public class AccountSecurityDataController {
    private final AccountSecurityDataService service;

    public AccountSecurityDataController(AccountSecurityDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountSecurityData> getAllAccountSecurityData() {
        return service.getAllAccountSecurityData();
    }


    @GetMapping("/{cpf}")
    public AccountSecurityData getAccountSecurityDataByCpf(@PathVariable String cpf) {
        return service.getAccountSecurityDataByCpf(cpf);
    }

    @PostMapping
    public AccountSecurityData createAccountSecurityData(@RequestBody AccountSecurityData accountSecurityData) {
        return service.createAccountSecurityData(accountSecurityData);
    }

    @PutMapping("/{cpf}")
    public AccountSecurityData updateAccountSecurityData(@PathVariable String cpf, @RequestBody AccountSecurityData updatedAccountSecurityData) {
        return service.updateAccountSecurityData(cpf, updatedAccountSecurityData);
    }

    @DeleteMapping("/{cpf}")
    public void deleteAccountSecurityData(@PathVariable String cpf) {
        service.deleteAccountSecurityData(cpf);
    }

}
