package com.api.bank.v1.core.controllers;

import com.api.bank.cache.Interface.CacheRepository;
import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.entity.AccountRequest;
import com.api.bank.v1.core.entity.AuthenticationRequest;
import com.api.bank.v1.core.service.AccountService;
import com.api.bank.v1.exception.AuthenticationException;
import com.api.bank.v1.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/V1/bank")
public class BankController {

    private final Logger logger = LogManager.getLogger(BankController.class);

    static {
        LogConfig.setLogFile(BankController.class.getName());
    }
    private final AccountService accountService;

    @Autowired
    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            Optional<Account> authenticatedAccount = accountService.authenticate(email, password);

            if (authenticatedAccount.isPresent()) {
                return ResponseEntity.ok(authenticatedAccount.get());
            } else {
                // Authentication failed, return BadRequest with error messages
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Authentication failed for user with email: " + email);
            }
        } catch (AuthenticationException e) {
            // Log the error if needed
            logger.error("Error during authentication for user with email: \n" + e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error during authentication for user with email: " + request.getEmail() + ". Reason: " + e.getMessage());
        } catch (RepositoryException e) {
            // Log the error if needed
            logger.error("Internal server error during authentication for user with email: \n" + e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error during authentication for user with email: " + request.getEmail());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountRequest accountRequest) {
        try {
            // Chamar o serviço para criar uma nova conta
            accountService.createAccount(accountRequest);

            return ResponseEntity.ok("Account created successfully.");
        } catch (RepositoryException e) {
            logger.error("Internal server error during account creation", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error during account creation");
        }
    }

    // Add other API endpoints for the BankController as needed for your application
}
