package com.api.bank.v1.core.service;

import com.api.bank.configuration.LogConfig;
import com.api.bank.v1.core.data.Account;
import com.api.bank.v1.core.data.Transaction;
import com.api.bank.v1.core.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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

    public void addTransactionToAccount(Long accountId, Transaction newTransaction) {
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

    public byte[] generateTransactionStatement(Long accountId) {
        byte[] pdfBytes = null;
        try (PDDocument document = new PDDocument()) {
            // Criar uma página no documento
            PDPage page = new PDPage();
            document.addPage(page);

            // Adicionar conteúdo à página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float fontSize = 24;
                float leading = 1.5f * fontSize;

                Optional<Account> optionalAccount = accountRepository.findById(accountId);

                if (optionalAccount.isPresent()) {
                    Account account = optionalAccount.get();


                    String title = "Extrato Bancário de " + account.getFullName();
                    float titleWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(title) / 1000 * fontSize;
                    float titleHeight = page.getMediaBox().getHeight() - 50;
                    float startX = (page.getMediaBox().getWidth() - titleWidth) / 2;

                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), fontSize);
                    contentStream.newLineAtOffset(startX, titleHeight);
                    contentStream.showText(title);
                    contentStream.newLine();
                    contentStream.newLineAtOffset(0, -leading * 3);

                    List<Transaction> transactions = account.getTransactions();

                    for (int i = 0; i < transactions.size(); i++) {
                        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                        contentStream.showText("Titulo: " + transactions.get(i).getTransactionName());
                        contentStream.newLine();
                        contentStream.newLineAtOffset(0, -leading);
                        contentStream.showText("Categoria: " + transactions.get(i).getTransactionCategory());
                        contentStream.newLine();
                        contentStream.newLineAtOffset(0, -leading);
                        contentStream.showText("Valor: " + transactions.get(i).getTransactionValue());
                        contentStream.newLine();
                        contentStream.newLineAtOffset(0, -leading);

                    }

                }
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            pdfBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfBytes;
    }




}
