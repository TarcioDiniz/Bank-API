package com.api.bank.v1.core.service;

import com.api.bank.v1.core.Enum.TransactionCategory;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.data.Transaction;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DepositService {

    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);


    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Autowired
    public DepositService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public void addDeposit(Long accountId, String description , BigDecimal amount) {
        try {
            Optional<Account> optionalAccount = accountRepository.findById(accountId);

            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();

                // Adicionar o valor ao saldo da conta
                BigDecimal currentBalance = account.getAccountBalance();
                BigDecimal newBalance = currentBalance.add(amount);
                account.setAccountBalance(newBalance);

                Transaction transaction1 = createTransaction(account, amount, description);

                // Add transactions to accounts
                transactionService.addTransactionToAccount(account.getId(), transaction1);

                // Salvar a conta atualizada
                accountRepository.save(account);

                logger.info("Deposit of {} added to the account with ID {}. New balance: {}.", amount, accountId, newBalance);
            } else {
                logger.error("Account with ID {} not found.", accountId);
                throw new RepositoryException("Account not found.");
            }
        } catch (Exception e) {
            logger.error("Error adding deposit to account with ID {}.", accountId, e);
            throw new RepositoryException("Error adding deposit.", e);
        }
    }

    private Transaction createTransaction(Account account, BigDecimal amount, String transactionName) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionName(transactionName);
        transaction.setTransactionCategory(TransactionCategory.Deposit.toString());
        transaction.setTransactionValue(amount);
        return transaction;
    }
}
