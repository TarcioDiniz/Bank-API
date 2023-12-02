package com.api.bank.v1.core.controllers;

import com.api.bank.v1.core.entity.UpdateAccountInfoRequest;
import com.api.bank.v1.core.service.AccountInfoUpdateService;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/V1/bank/account")
public class AccountInfoUpdateController {

    private final Logger logger = LoggerFactory.getLogger(AccountInfoUpdateController.class);

    private final AccountInfoUpdateService accountInfoUpdateService;

    @Autowired
    public AccountInfoUpdateController(AccountInfoUpdateService accountInfoUpdateService) {
        this.accountInfoUpdateService = accountInfoUpdateService;
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<?> updateAccountInfo(@RequestBody UpdateAccountInfoRequest request) {
        try {
            Long accountId = request.getId();
            String newContact = request.getContact();
            String newEmail = request.getEmail();
            String newPassword = request.getPassword();

            accountInfoUpdateService.updateAccountInfo(accountId, newContact, newEmail, newPassword);

            return ResponseEntity.ok("Account information updated successfully.");
        } catch (RepositoryException e) {
            logger.error("Internal server error during account information update", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error during account information update");
        }
    }
}
