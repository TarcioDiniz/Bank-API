package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.BankData;
import com.api.bank.BankApiLayer.Services.BankDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankDataController {

    /*
    * @Autowired
    * private BankService service;
    * */

    private final BankDataService service;

    public BankDataController(BankDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<BankData> getAllBankData() {
        return service.getAllBankData();
    }

    @GetMapping("/{cpf}")
    public BankData getBankDataByCpf(@PathVariable String cpf) {
        return service.getBankDataByCpf(cpf);
    }

    @PostMapping
    public BankData createBankData(@RequestBody BankData bankData) {
        return service.createBankData(bankData);
    }

    @PutMapping("/{cpf}")
    public BankData updateBankData(@PathVariable String cpf, @RequestBody BankData updatedBankData) {
        return service.updateBankData(cpf, updatedBankData);
    }

    @DeleteMapping("/{cpf}")
    public void deleteBankData(@PathVariable String cpf) {
        service.deleteBankData(cpf);
    }
}
