package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.data.CreditCard;
import com.api.bank.v1.core.entity.AccountRequest;
import com.api.bank.v1.core.repository.AccountRepository;
import com.api.bank.v1.exception.AuthenticationException;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    static {
        LogConfig.setLogFile(AccountService.class.getName());
    }

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }


    public Optional<Account> authenticate(String email, String password) {
        try {
            logger.info("Authenticating user with email: {}", email);
            Optional<Account> authenticatedAccount = repository.findByEmailAndPassword(email, password);

            if (authenticatedAccount.isPresent()) {
                return authenticatedAccount;
            } else {
                throw new AuthenticationException("Authentication failed for user with email: " + email);
            }
        } catch (Exception e) {
            logger.error("Error during authentication for user with email: {}", email, e);
            throw new RepositoryException("Error during authentication", e);
        }
    }

    public void createAccount(AccountRequest accountRequest) {
        try {
            Random random = new Random();

            // Criar uma nova conta com os dados fornecidos e outros campos como null ou vazios
            Account newAccount = new Account();
            newAccount.setFullName(accountRequest.fullName);
            newAccount.setCpf(accountRequest.cpf);
            newAccount.setPhoneContact(accountRequest.phoneContact);
            newAccount.setEmail(accountRequest.email);
            newAccount.setPassword(accountRequest.password);
            newAccount.setBankAccount(random.nextInt(999) + 1);
            newAccount.setBankBranch("2979");

            // Preencher outros campos com valores padrão (null ou vazios)
            newAccount.setPixKeys(Collections.emptyList());
            newAccount.setQuickTransferFriends(Collections.emptyList());

            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber("5144565043139946");
            creditCard.setCreditLimit(new BigDecimal("100"));
            creditCard.setExpirationDate("29/05/2025");
            creditCard.setCvv("116");

            newAccount.setCreditCard(creditCard);
            newAccount.setAccountBalance(BigDecimal.ZERO);
            newAccount.setIncome(BigDecimal.ZERO);
            newAccount.setExpenses(BigDecimal.ZERO);

            // Salvar a nova conta
            newAccount = repository.save(newAccount);

            logger.info("Account created successfully with id: {}", newAccount.getId());

        } catch (Exception e) {
            logger.error("Error during account creation", e);
            throw new RepositoryException("Error during account creation", e);
        }
    }

}
