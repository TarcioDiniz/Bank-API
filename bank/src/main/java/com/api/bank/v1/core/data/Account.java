package com.api.bank.v1.core.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "bank_account")
    private Integer bankAccount;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "phone_contact")
    private String phoneContact;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ElementCollection
    @CollectionTable(name = "pix_keys", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "pix_key")
    private List<String> pixKeys;

    @ElementCollection
    @CollectionTable(name = "quick_transfer_friends", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "friend_name")
    private List<String> quickTransferFriends;

    @Embedded
    private CreditCard creditCard;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "expenses")
    private BigDecimal expenses;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPixKeys() {
        return pixKeys;
    }

    public void setPixKeys(List<String> pixKeys) {
        this.pixKeys = pixKeys;
    }

    public List<String> getQuickTransferFriends() {
        return quickTransferFriends;
    }

    public void setQuickTransferFriends(List<String> quickTransferFriends) {
        this.quickTransferFriends = quickTransferFriends;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    // Getters and setters

    // Other constructors, methods, and nested classes
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

}
