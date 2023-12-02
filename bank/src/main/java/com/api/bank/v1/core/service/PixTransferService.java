package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.core.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.bank.v1.core.service.PixTransfer;




import java.math.BigDecimal;

@Service
public class PixTransferService {

    private static final Logger logger = LoggerFactory.getLogger(PixTransferService.class);

    static {
        LogConfig.setLogFile(PixTransferService.class.getName());
    }

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public PixTransferService(TransactionService transactionService, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void addPixTransferToAccount(Long accountId, String recipientName, String recipientAccountNumber, BigDecimal transferAmount) {
        logger.info("Adding PIX transfer to account with ID: {}", accountId);

        // Validar a transferência PIX, se necessário

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    logger.error("Conta com ID {} não encontrada. Lançando EntityNotFoundException.", accountId);
                    return new EntityNotFoundException("Conta com ID " + accountId + " não encontrada.");
                });

        // Criar uma nova instância de PixTransfer
        PixTransfer pixTransfer = new PixTransfer();
        pixTransfer.setRecipientName(recipientName);
        pixTransfer.setRecipientAccountNumber(recipientAccountNumber);
        pixTransfer.setTransferAmount(transferAmount);

        // Certifique-se de que a transferência PIX tenha uma referência válida para a conta
        pixTransfer.setAccount(account);

        // Atualizar o saldo da conta com o valor da transferência PIX
        BigDecimal newBalance = account.getAccountBalance().subtract(transferAmount);
        account.setAccountBalance(newBalance);

        // Salvar a conta atualizada
        accountRepository.save(account);

        // Adicionar a transferência PIX como uma transação
        transactionService.addTransactionToAccount(accountId, pixTransfer);

        logger.info("PIX transfer added successfully to account with ID: {}", accountId);
    }
}
