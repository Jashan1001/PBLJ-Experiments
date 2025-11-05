package com.example.partc.service;

import com.example.partc.model.Account;
import com.example.partc.model.Transaction;
import com.example.partc.repository.AccountRepository;
import com.example.partc.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Banking Service with Spring Transaction Management
 * Demonstrates @Transactional annotation for managing transactions
 * Part C: Transaction Management Using Spring and Hibernate
 */
@Service
public class BankingService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    /**
     * Create a new account (Transactional)
     */
    @Transactional
    public void createAccount(Account account) {
        System.out.println("\n=== Creating New Account ===");
        accountRepository.save(account);
    }
    
    /**
     * Deposit money into an account (Transactional)
     */
    @Transactional
    public void deposit(String accountNumber, Double amount) {
        System.out.println("\n=== Processing Deposit ===");
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountNumber);
        }
        
        account.setBalance(account.getBalance() + amount);
        accountRepository.update(account);
        
        // Log transaction
        Transaction transaction = new Transaction(
            null, accountNumber, amount, "DEPOSIT", "SUCCESS");
        transactionRepository.save(transaction);
        
        System.out.println("Deposited " + amount + " to account " + accountNumber);
        System.out.println("New balance: " + account.getBalance());
    }
    
    /**
     * Withdraw money from an account (Transactional)
     */
    @Transactional
    public void withdraw(String accountNumber, Double amount) {
        System.out.println("\n=== Processing Withdrawal ===");
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountNumber);
        }
        
        if (account.getBalance() < amount) {
            // This will cause transaction rollback
            throw new RuntimeException("Insufficient balance. Current balance: " + account.getBalance());
        }
        
        account.setBalance(account.getBalance() - amount);
        accountRepository.update(account);
        
        // Log transaction
        Transaction transaction = new Transaction(
            accountNumber, null, amount, "WITHDRAWAL", "SUCCESS");
        transactionRepository.save(transaction);
        
        System.out.println("Withdrawn " + amount + " from account " + accountNumber);
        System.out.println("New balance: " + account.getBalance());
    }
    
    /**
     * Transfer money between accounts (Transactional)
     * This demonstrates atomicity - either both operations succeed or both fail
     */
    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, Double amount) {
        System.out.println("\n=== Processing Transfer ===");
        System.out.println("From: " + fromAccountNumber + " To: " + toAccountNumber + " Amount: " + amount);
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        // Get both accounts
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        
        if (fromAccount == null) {
            throw new RuntimeException("Source account not found: " + fromAccountNumber);
        }
        
        if (toAccount == null) {
            throw new RuntimeException("Destination account not found: " + toAccountNumber);
        }
        
        if (fromAccount.getBalance() < amount) {
            // This will cause transaction rollback
            throw new RuntimeException("Insufficient balance in source account. Current balance: " 
                + fromAccount.getBalance());
        }
        
        // Perform transfer - both operations will be committed together
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);
        
        // Log transaction
        Transaction transaction = new Transaction(
            fromAccountNumber, toAccountNumber, amount, "TRANSFER", "SUCCESS");
        transactionRepository.save(transaction);
        
        System.out.println("Transfer successful!");
        System.out.println(fromAccountNumber + " new balance: " + fromAccount.getBalance());
        System.out.println(toAccountNumber + " new balance: " + toAccount.getBalance());
    }
    
    /**
     * Get account by account number (Read-only transaction)
     */
    @Transactional(readOnly = true)
    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    /**
     * Get all accounts (Read-only transaction)
     */
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    /**
     * Get all transactions (Read-only transaction)
     */
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    /**
     * Get transactions for a specific account (Read-only transaction)
     */
    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactions(String accountNumber) {
        return transactionRepository.findByAccount(accountNumber);
    }
}

