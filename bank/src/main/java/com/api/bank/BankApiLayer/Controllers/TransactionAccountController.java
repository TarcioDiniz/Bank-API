package com.api.bank.BankApiLayer.Controllers;


import com.api.bank.BankApiLayer.Entity.Model.Balance;
import com.api.bank.BankApiLayer.Entity.Model.Transaction;
import com.api.bank.BankApiLayer.Services.AccountBalanceDataService;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;
import com.api.bank.BankApiLayer.Services.TransactionAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionAccountController {

    private final TransactionAccountService transactionAccountService;
    private final AccountBalanceDataService accountBalanceDataService;

    public TransactionAccountController(
            TransactionAccountService transactionAccountService,
            AccountBalanceDataService accountBalanceDataService) {
        this.transactionAccountService = transactionAccountService;
        this.accountBalanceDataService = accountBalanceDataService;
    }

    @GetMapping("/transaction/{cpf}")
    public Balance getAccountBalanceDataByCpf(@PathVariable String cpf) {
        return accountBalanceDataService.getAccountBalanceDataByCpf(cpf);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositAccount(@RequestBody Transaction transaction) {
        try {
            transactionAccountService.createDeposit(transaction);
            return ResponseEntity.ok("Deposito feito com sucesso");
        } catch (RegisterAccountService.ExceptionRegister e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawAccount(@RequestBody Transaction transaction) {
        try {
            transactionAccountService.withdrawAccount(transaction);
            return ResponseEntity.ok("Saque feito com sucesso");
        } catch (RegisterAccountService.ExceptionRegister e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }

}
