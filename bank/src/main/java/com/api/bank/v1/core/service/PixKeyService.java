package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PixKeyService {

    private static final Logger logger = LoggerFactory.getLogger(PixKeyService.class);

    static {
        LogConfig.setLogFile(PixKeyService.class.getName());
    }

    private final AccountRepository accountRepository;

    @Autowired
    public PixKeyService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addPixKey(Long accountId, String pixKey) {
        try {
            Optional<Account> optionalAccount = accountRepository.findById(accountId);

            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();

                // Verificar se a chave PIX já existe
                if (account.getPixKeys().contains(pixKey)) {
                    logger.warn("Pix Key {} already exists.", pixKey);
                    throw new RepositoryException("Pix Key already exists.");
                }

                // Adicionar a nova chave PIX
                List<String> pixKeys = account.getPixKeys();
                pixKeys.add(pixKey);
                account.setPixKeys(pixKeys);

                // Salvar a conta atualizada
                accountRepository.save(account);
                logger.info("Pix Key {} added to the account with ID {}.", pixKey, accountId);
            } else {
                logger.error("Account with ID {} not found.", accountId);
                throw new RepositoryException("Account not found.");
            }
        } catch (Exception e) {
            logger.error("Error adding Pix Key to account with ID {}.", accountId, e);
            throw new RepositoryException("Error adding Pix Key.", e);
        }
    }
}
