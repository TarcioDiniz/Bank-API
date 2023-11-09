package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.Data.AccountSecurityData;
import com.api.bank.BankApiLayer.Entity.Data.BankData;
import com.api.bank.BankApiLayer.Entity.Data.PersonalData;
import com.api.bank.BankApiLayer.Entity.Model.RegisterAccount;
import com.api.bank.BankApiLayer.Enum.BankConst;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterAccountController {
    private final RegisterAccountService registerAccountService;

    public RegisterAccountController(RegisterAccountService registerAccountService) {
        this.registerAccountService = registerAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegisterAccount registerAccount) {
        try {
            // Chame o serviço para criar a conta
            BankData bankData = new BankData();
            PersonalData personalData = new PersonalData();
            AccountSecurityData accountSecurityData = new AccountSecurityData();

            bankData.setCompleteBankData(registerAccount.getCpf(), BankConst.AGENCY1.getValue());
            personalData.setCompletePersonalData(registerAccount.getCpf(), registerAccount.getName(), registerAccount.getDate(), registerAccount.getEmail(), registerAccount.getTypeAccount());
            accountSecurityData.setCompleteAccountSecurityData(registerAccount.getCpf(), registerAccount.getPassword());

            registerAccountService.createAccount(bankData, personalData, accountSecurityData
            );

            return ResponseEntity.ok("Conta criada com sucesso");
        } catch (RegisterAccountService.ExceptionRegister e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }
}
