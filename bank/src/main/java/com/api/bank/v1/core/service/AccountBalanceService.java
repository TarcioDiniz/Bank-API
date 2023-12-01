package com.api.bank.v1.core.service;

import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountBalanceService {

    private static final Logger logger = LoggerFactory.getLogger(AccountBalanceService.class);

    private final AccountRepository accountRepository;

    @Autowired
    public AccountBalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BigDecimal getAccountBalance(Long accountId) {
        try {
            Optional<Account> optionalAccount = accountRepository.findById(accountId);

            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                return account.getAccountBalance();
            } else {
                logger.warn("Account with ID {} not found.", accountId);
                throw new RepositoryException("Account not found.");
            }
        } catch (Exception e) {
            logger.error("Error getting account balance for account with ID {}.", accountId, e);
            throw new RepositoryException("Error getting account balance.", e);
        }
    }
}
