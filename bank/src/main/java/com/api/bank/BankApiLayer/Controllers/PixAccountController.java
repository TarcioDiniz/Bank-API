package com.api.bank.BankApiLayer.Controllers;

import com.api.bank.BankApiLayer.Entity.Model.PixAccount;
import com.api.bank.BankApiLayer.Services.PixAccountDataService;
import com.api.bank.BankApiLayer.Services.RegisterAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PixAccountController {
    private final PixAccountDataService pixAccountDataService;

    public PixAccountController(PixAccountDataService pixAccountDataService){
        this.pixAccountDataService = pixAccountDataService;
    }

    @GetMapping("/key-pix/{cpf}")
    public PixAccount getPixAccountDataByCpf(@PathVariable String cpf) {
        return pixAccountDataService.getPixAccountDataByCpf(cpf);
    }

    @PostMapping("/register/key-pix")
    public ResponseEntity<String> registerPixKeyAccount(@RequestBody PixAccount pixAccount) {
        try {
            pixAccountDataService.createKeyPix(pixAccount);
            return ResponseEntity.ok("Chave Pix Cadastrada com sucesso");
        } catch (RegisterAccountService.ExceptionRegister e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }

}
