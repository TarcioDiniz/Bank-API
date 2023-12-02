package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    static {
        LogConfig.setLogFile(TransactionService.class.getName());
    }

    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addTransactionToAccount(Long accountId, PixTransfer newTransaction) {
        logger.info("Adding transaction to account with ID: {}", accountId);

        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            // Certifique-se de que a transação tenha uma referência válida para a conta
            newTransaction.setAccount(account);
            account.addTransaction(newTransaction);
            accountRepository.save(account);

            logger.info("Transaction added successfully to account with ID: {}", accountId);
        } else {
            logger.error("Conta com ID {} não encontrada. Lançando EntityNotFoundException.", accountId);
            throw new EntityNotFoundException("Conta com ID " + accountId + " não encontrada.");
        }
    }
}