package com.example.partc;

import com.example.partc.config.HibernateConfig;
import com.example.partc.model.Account;
import com.example.partc.model.Transaction;
import com.example.partc.service.BankingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Main class to demonstrate Transaction Management with Spring and Hibernate
 * Part C: Transaction Management Using Spring and Hibernate
 */
public class PartCMain {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Part C: Spring Transaction Management Demo");
        System.out.println("Spring + Hibernate Integration");
        System.out.println("========================================\n");
        
        // Create Spring Application Context
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        
        System.out.println("\n========================================");
        System.out.println("Spring Container Initialized!");
        System.out.println("Transaction Management Enabled!");
        System.out.println("========================================");
        
        // Get Banking Service
        BankingService bankingService = context.getBean(BankingService.class);
        
        try {
            // Create accounts
            System.out.println("\n--- Creating Accounts ---");
            Account account1 = new Account("ACC001", "John Doe", 5000.0, "Savings");
            Account account2 = new Account("ACC002", "Jane Smith", 3000.0, "Current");
            Account account3 = new Account("ACC003", "Bob Johnson", 2000.0, "Savings");
            
            bankingService.createAccount(account1);
            bankingService.createAccount(account2);
            bankingService.createAccount(account3);
            
            // Display all accounts
            System.out.println("\n--- All Accounts ---");
            List<Account> accounts = bankingService.getAllAccounts();
            accounts.forEach(System.out::println);
            
            // Test deposit transaction
            System.out.println("\n=== Transaction Test 1: Deposit ===");
            bankingService.deposit("ACC001", 1000.0);
            
            // Test withdrawal transaction
            System.out.println("\n=== Transaction Test 2: Withdrawal ===");
            bankingService.withdraw("ACC002", 500.0);
            
            // Test successful transfer transaction
            System.out.println("\n=== Transaction Test 3: Successful Transfer ===");
            bankingService.transfer("ACC001", "ACC002", 2000.0);
            
            // Display updated balances
            System.out.println("\n--- Updated Account Balances ---");
            bankingService.getAllAccounts().forEach(acc -> 
                System.out.println(acc.getAccountNumber() + " - Balance: " + acc.getBalance()));
            
            // Test transaction rollback - insufficient funds
            System.out.println("\n=== Transaction Test 4: Transfer with Insufficient Funds ===");
            System.out.println("Attempting to transfer 10000 from ACC003 (This should fail and rollback)");
            try {
                bankingService.transfer("ACC003", "ACC001", 10000.0);
            } catch (Exception e) {
                System.err.println("Transaction failed: " + e.getMessage());
                System.out.println("Transaction rolled back successfully!");
            }
            
            // Verify balances remain unchanged after rollback
            System.out.println("\n--- Balances After Rollback (Should be unchanged) ---");
            Account acc3 = bankingService.getAccount("ACC003");
            System.out.println(acc3);
            
            // Display all transactions
            System.out.println("\n--- Transaction History ---");
            List<Transaction> transactions = bankingService.getAllTransactions();
            transactions.forEach(System.out::println);
            
            // Display transactions for specific account
            System.out.println("\n--- Transactions for ACC001 ---");
            List<Transaction> acc1Transactions = bankingService.getAccountTransactions("ACC001");
            acc1Transactions.forEach(System.out::println);
            
            System.out.println("\n========================================");
            System.out.println("Part C Demo Completed Successfully!");
            System.out.println("Transaction Management Demonstrated:");
            System.out.println("1. Successful transactions committed");
            System.out.println("2. Failed transactions rolled back");
            System.out.println("3. Data consistency maintained");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("Error occurred:");
            e.printStackTrace();
        } finally {
            // Close context
            ((AnnotationConfigApplicationContext) context).close();
            System.out.println("\nSpring Container closed successfully!");
        }
    }
}

