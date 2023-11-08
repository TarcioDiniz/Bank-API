package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.PersonalData;
import com.api.bank.BankApiLayer.Entity.TransactionHistoryData;
import com.api.bank.BankApiLayer.Services.TransactionHistoryDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactionHistory")
public class TransactionHistoryDataController {

    private final TransactionHistoryDataService service;

    public TransactionHistoryDataController(TransactionHistoryDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionHistoryData> getAllTransactionHistoryData() {
        return service.getAllTransactionHistoryData();
    }


    @GetMapping("/{cpf}")
    public TransactionHistoryData getTransactionHistoryDataByCpf(@PathVariable String cpf) {
        return service.getTransactionHistoryDataByCpf(cpf);
    }

    @PostMapping
    public TransactionHistoryData createTransactionHistoryData(@RequestBody TransactionHistoryData TransactionHistoryData) {
        return service.createTransactionHistoryData(TransactionHistoryData);
    }

    @PutMapping("/{cpf}")
    public TransactionHistoryData updateTransactionHistoryData(@PathVariable String cpf, @RequestBody TransactionHistoryData updatedTransactionHistoryData) {
        return service.updateTransactionHistoryData(cpf, updatedTransactionHistoryData);
    }

    @DeleteMapping("/{cpf}")
    public void deleteTransactionHistoryData(@PathVariable String cpf) {
        service.deleteTransactionHistoryData(cpf);
    }

}
