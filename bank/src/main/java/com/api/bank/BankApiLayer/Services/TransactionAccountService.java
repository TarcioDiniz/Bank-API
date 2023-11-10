package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.Data.AccountBalanceData;
import com.api.bank.BankApiLayer.Entity.Data.TransactionHistoryData;
import com.api.bank.BankApiLayer.Entity.Model.Transaction;
import com.api.bank.BankApiLayer.Enum.TypeTransactions;
import com.api.bank.BankApiLayer.Repository.AccountBalanceDataRepository;
import com.api.bank.BankApiLayer.Repository.BankDataRepository;
import com.api.bank.BankApiLayer.Repository.TransactionHistoryDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

import static com.api.bank.BankApiLayer.Exception.ExceptionAccount.validateAccount;
import static com.api.bank.BankApiLayer.Exception.ExceptionAccount.validateAccountValueBalance;
import static com.api.bank.BankApiLayer.Exception.ExceptionCPF.validateCPForCNJP;
import static com.api.bank.BankApiLayer.Exception.ExceptionCPF.validateCpfNotNull;

@Service
public class TransactionAccountService {

    private final AccountBalanceDataRepository accountBalanceDataRepository;
    private final TransactionHistoryDataRepository transactionHistoryDataRepository;
    private final BankDataRepository bankDataRepository;


    public TransactionAccountService(
            AccountBalanceDataRepository accountBalanceDataRepository,
            TransactionHistoryDataRepository transactionHistoryDataRepository,
            BankDataRepository bankDataRepository
    ) {
        this.accountBalanceDataRepository = accountBalanceDataRepository;
        this.transactionHistoryDataRepository = transactionHistoryDataRepository;
        this.bankDataRepository = bankDataRepository;
    }

    @Transactional
    public void createDeposit(Transaction transaction) {

        validateCpfNotNull(transaction.getCpf());
        validateCPForCNJP(transaction.getCpf());
        validateAccount(transaction.getCpf(), bankDataRepository);


        BigDecimal balance = accountBalanceDataRepository.findByCpf(transaction.getCpf()).getValue();

        AccountBalanceData accountBalanceData = new AccountBalanceData();
        accountBalanceData.setCpf(transaction.getCpf());
        accountBalanceData.setCurrency("brl");
        accountBalanceData.setValue(balance.add(transaction.getValue()));


        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();

        TransactionHistoryData transactionHistoryData = new TransactionHistoryData();
        transactionHistoryData.setCpf(transaction.getCpf());
        transactionHistoryData.setDescription(transaction.getDescription());
        transactionHistoryData.setType_transactions(TypeTransactions.DEPOSIT.getValue());
        transactionHistoryData.setTimestamp(timestamp);
        transactionHistoryData.setValue(transaction.getValue().toString());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);

    }
    @Transactional
    public void withdrawAccount(Transaction transaction) {

        validateCpfNotNull(transaction.getCpf());
        validateCPForCNJP(transaction.getCpf());
        validateAccount(transaction.getCpf(), bankDataRepository);
        validateAccountValueBalance(transaction.getCpf(), transaction.getValue(), accountBalanceDataRepository);


        BigDecimal balance = accountBalanceDataRepository.findByCpf(transaction.getCpf()).getValue();

        AccountBalanceData accountBalanceData = new AccountBalanceData();
        accountBalanceData.setCpf(transaction.getCpf());
        accountBalanceData.setCurrency("brl");
        accountBalanceData.setValue(balance.subtract(transaction.getValue()));


        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();

        TransactionHistoryData transactionHistoryData = new TransactionHistoryData();
        transactionHistoryData.setCpf(transaction.getCpf());
        transactionHistoryData.setDescription(transaction.getDescription());
        transactionHistoryData.setType_transactions(TypeTransactions.WITHDRAW.getValue());
        transactionHistoryData.setTimestamp(timestamp);
        transactionHistoryData.setValue(transaction.getValue().toString());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);

    }

}
