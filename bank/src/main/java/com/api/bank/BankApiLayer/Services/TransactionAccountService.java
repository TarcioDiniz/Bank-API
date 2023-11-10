package com.api.bank.BankApiLayer.Services;

import com.api.bank.BankApiLayer.Entity.Data.AccountBalanceData;
import com.api.bank.BankApiLayer.Entity.Data.TransactionHistoryData;
import com.api.bank.BankApiLayer.Entity.Model.transaction.Pix;
import com.api.bank.BankApiLayer.Entity.Model.transaction.Transaction;
import com.api.bank.BankApiLayer.Entity.Model.transaction.TransactionAccountID;
import com.api.bank.BankApiLayer.Enum.TypeTransactions;
import com.api.bank.BankApiLayer.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

import static com.api.bank.BankApiLayer.Exception.ExceptionGenerics.*;

@Service
public class TransactionAccountService {

    private final AccountBalanceDataRepository accountBalanceDataRepository;
    private final TransactionHistoryDataRepository transactionHistoryDataRepository;
    private final BankDataRepository bankDataRepository;
    private final PixAccountDataRepository pixAccountDataRepository;
    private final PersonalDataRepository personalDataRepository;


    public TransactionAccountService(
            AccountBalanceDataRepository accountBalanceDataRepository,
            TransactionHistoryDataRepository transactionHistoryDataRepository,
            BankDataRepository bankDataRepository,
            PixAccountDataRepository pixAccountDataRepository,
            PersonalDataRepository personalDataRepository
    ) {
        this.accountBalanceDataRepository = accountBalanceDataRepository;
        this.transactionHistoryDataRepository = transactionHistoryDataRepository;
        this.bankDataRepository = bankDataRepository;
        this.pixAccountDataRepository = pixAccountDataRepository;
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    public void createDeposit(Transaction transaction) {

        validateCPFNotNull(transaction.getCpf());
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
        transactionHistoryData.setComplete(
                transaction.getCpf(), transaction.getValue().toString(),
                timestamp, transaction.getDescription(), TypeTransactions.DEPOSIT.getValue());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);
    }

    @Transactional
    public void withdrawAccount(Transaction transaction) {

        validateCPFNotNull(transaction.getCpf());
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
        transactionHistoryData.setComplete(
                transaction.getCpf(), transaction.getValue().toString(),
                timestamp, transaction.getDescription(), TypeTransactions.WITHDRAW.getValue());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);

    }

    @Transactional
    public void pixToOtherAccount(Pix pix) {

        validateCPFNotNull(pix.getThisCpf());
        validateCPForCNJP(pix.getThisCpf());
        validateAccount(pix.getThisCpf(), bankDataRepository);
        validateAccountValueBalance(pix.getThisCpf(), pix.getValue(), accountBalanceDataRepository);
        validatePixKey(pix.getPixKey(), pixAccountDataRepository);


        BigDecimal balance = accountBalanceDataRepository.findByCpf(pix.getThisCpf()).getValue();


        // this account
        AccountBalanceData accountBalanceData = new AccountBalanceData();
        accountBalanceData.setCpf(pix.getThisCpf());
        accountBalanceData.setCurrency("brl");
        accountBalanceData.setValue(balance.subtract(pix.getValue()));

        // other account
        AccountBalanceData otherBalanceData = new AccountBalanceData();
        otherBalanceData.setCpf(
                pixAccountDataRepository.findByPixKey(pix.getPixKey()).getCpf()
        );
        BigDecimal otherValue = accountBalanceDataRepository.findByCpf(pixAccountDataRepository.findByPixKey(pix.getPixKey()).getCpf()).getValue();
        otherValue = otherValue.add(pix.getValue());
        otherBalanceData.setCurrency("brl");
        otherBalanceData.setValue(otherValue);

        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();


        // this transaction
        TransactionHistoryData transactionHistoryData = new TransactionHistoryData();
        transactionHistoryData.setComplete(
                pix.getThisCpf(), pix.getValue().toString(),
                timestamp, pix.getDescription(), TypeTransactions.PIX.getValue());


        // other transaction
        TransactionHistoryData othertransactionHistoryData = new TransactionHistoryData();
        String otherDescription = "Você recebeu um PIX de " +
                personalDataRepository.findByCpf(pix.getThisCpf()).getFull_name() + ".";
        othertransactionHistoryData.setComplete(
                pixAccountDataRepository.findByPixKey(pix.getPixKey()).getCpf(), pix.getValue().toString(),
                timestamp, otherDescription, TypeTransactions.PIX.getValue());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);

        accountBalanceDataRepository.save(otherBalanceData);
        transactionHistoryDataRepository.save(othertransactionHistoryData);

    }

    @Transactional
    public void transactionToOtherAccount(TransactionAccountID transactionAccountID) {
        validateCPFNotNull(transactionAccountID.getThisCpf());
        validateCPForCNJP(transactionAccountID.getThisCpf());
        validateAccount(transactionAccountID.getThisCpf(), bankDataRepository);
        validateAccountValueBalance(transactionAccountID.getThisCpf(), transactionAccountID.getValue(), accountBalanceDataRepository);
        validadeBankDataAccount(transactionAccountID.getAccount_id(), bankDataRepository);


        BigDecimal balance = accountBalanceDataRepository.findByCpf(transactionAccountID.getThisCpf()).getValue();


        // this account
        AccountBalanceData accountBalanceData = new AccountBalanceData();
        accountBalanceData.setCpf(transactionAccountID.getThisCpf());
        accountBalanceData.setCurrency("brl");
        accountBalanceData.setValue(balance.subtract(transactionAccountID.getValue()));

        // other account
        AccountBalanceData otherBalanceData = new AccountBalanceData();
        otherBalanceData.setCpf(
                bankDataRepository.findByAccount(transactionAccountID.getAccount_id()).getCpf()
        );
        BigDecimal otherValue = accountBalanceDataRepository.findByCpf(bankDataRepository.findByAccount(transactionAccountID.getAccount_id()).getCpf()).getValue();
        otherValue = otherValue.add(transactionAccountID.getValue());
        otherBalanceData.setCurrency("brl");
        otherBalanceData.setValue(otherValue);

        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();


        // this transaction
        TransactionHistoryData transactionHistoryData = new TransactionHistoryData();
        transactionHistoryData.setComplete(
                transactionAccountID.getThisCpf(), transactionAccountID.getValue().toString(),
                timestamp, transactionAccountID.getDescription(), TypeTransactions.TRANSFER_ACCOUNT.getValue());


        // other transaction
        TransactionHistoryData othertransactionHistoryData = new TransactionHistoryData();
        String otherDescription = "Você recebeu uma transferência de " +
                personalDataRepository.findByCpf(transactionAccountID.getThisCpf()).getFull_name() + ".";
        othertransactionHistoryData.setComplete(
                bankDataRepository.findByAccount(transactionAccountID.getAccount_id()).getCpf(),
                transactionAccountID.getValue().toString(),
                timestamp, otherDescription, TypeTransactions.TRANSFER_ACCOUNT.getValue());

        accountBalanceDataRepository.save(accountBalanceData);
        transactionHistoryDataRepository.save(transactionHistoryData);

        accountBalanceDataRepository.save(otherBalanceData);
        transactionHistoryDataRepository.save(othertransactionHistoryData);
    }
}
