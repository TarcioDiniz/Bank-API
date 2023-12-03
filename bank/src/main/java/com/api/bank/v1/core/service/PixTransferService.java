package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.Enum.TransactionCategory;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.data.Transaction;
import com.api.bank.v1.core.entity.PixTransferRequest;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.exception.AccountNotFoundException;
import com.api.bank.v1.exception.InsufficientBalanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PixTransferService {

    private static final Logger logger = LoggerFactory.getLogger(PixTransferService.class);

    static {
        LogConfig.setLogFile(PixTransferService.class.getName());
    }

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final AccountBalanceService accountBalanceService;

    @Autowired
    public PixTransferService(TransactionService transactionService, AccountRepository accountRepository, AccountBalanceService accountBalanceService) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.accountBalanceService = accountBalanceService;
    }

    public void addPixTransferToAccount(PixTransferRequest pixTransfer) {

        Optional<Account> optionalAccount1 = accountRepository.findById(pixTransfer.getAccountId());
        Optional<Account> optionalAccount2 = accountRepository.findByPixKeys(pixTransfer.getPixKey());

        if (pixTransfer.getTransferAmount().compareTo(BigDecimal.ZERO) > 0) {
            if (optionalAccount1.isPresent() && optionalAccount2.isPresent()) {
                Account account1 = optionalAccount1.get();
                Account account2 = optionalAccount2.get();

                BigDecimal transferAmount = pixTransfer.getTransferAmount();
                BigDecimal account1Balance = accountBalanceService.getAccountBalance(account1.getId());

                if (account1Balance.compareTo(transferAmount) >= 0) {
                    // Perform the transfer
                    performPixTransfer(account1, account2, transferAmount, pixTransfer.getTransactionName());
                    logger.info("Pix transfer completed successfully.");
                } else {
                    logger.error("Insufficient balance for Pix transfer. Pix transfer failed.");
                    throw new InsufficientBalanceException("Insufficient balance for Pix transfer");
                }
            } else {
                logger.error("One or both accounts not found. Pix transfer failed.");
                throw new AccountNotFoundException("One or both accounts not found. Pix transfer failed.");
            }
        } else {
            logger.error("Value " + pixTransfer.getTransferAmount() + " is negative.");
            throw new AccountNotFoundException("Value " + pixTransfer.getTransferAmount() + " is negative.");
        }

    }

    private void performPixTransfer(Account account1, Account account2, BigDecimal transferAmount, String transactionName) {
        // Deduct amount from account1
        account1.setAccountBalance(account1.getAccountBalance().subtract(transferAmount));

        // Add amount to account2
        account2.setAccountBalance(account2.getAccountBalance().add(transferAmount));

        // Create transactions
        Transaction transaction1 = createTransaction(account1, transferAmount.negate(), transactionName + " to " + account2.getFullName());
        Transaction transaction2 = createTransaction(account2, transferAmount, "You received a pix from " + account1.getFullName());

        // Add transactions to accounts
        transactionService.addTransactionToAccount(account1.getId(), transaction1);
        transactionService.addTransactionToAccount(account2.getId(), transaction2);

        // Save accounts
        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    private Transaction createTransaction(Account account, BigDecimal amount, String transactionName) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionName(transactionName);
        transaction.setTransactionCategory(TransactionCategory.Pix.toString());
        transaction.setTransactionValue(amount);
        return transaction;
    }
}
