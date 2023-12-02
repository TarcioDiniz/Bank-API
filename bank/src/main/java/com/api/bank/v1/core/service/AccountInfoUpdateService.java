package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(AccountInfoUpdateService.class);

    static {
        LogConfig.setLogFile(AccountInfoUpdateService.class.getName());
    }

    private final AccountRepository accountRepository;

    @Autowired
    public AccountInfoUpdateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void updateAccountInfo(Long accountId, String newContact, String newEmail, String newPassword) {
        logger.info("Updating account information for account with ID: {}", accountId);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    logger.error("Account with ID {} not found. Throwing EntityNotFoundException.", accountId);
                    return new EntityNotFoundException("Account with ID " + accountId + " not found.");
                });

        // Atualizar as informações da conta
        if (newContact != null) {
            account.setPhoneContact(newContact);
        }

        if (newEmail != null) {
            account.setEmail(newEmail);
        }

        if (newPassword != null) {
            account.setPassword(newPassword);
        }

        // Salvar a conta atualizada
        accountRepository.save(account);

        logger.info("Account information updated successfully for account with ID: {}", accountId);
    }
}
