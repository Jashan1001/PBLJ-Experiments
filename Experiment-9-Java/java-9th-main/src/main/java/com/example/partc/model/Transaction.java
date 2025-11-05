package com.example.partc.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Transaction Entity to log all transactions
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    
    @Column(name = "from_account")
    private String fromAccount;
    
    @Column(name = "to_account")
    private String toAccount;
    
    @Column(name = "amount", nullable = false)
    private Double amount;
    
    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER
    
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    
    @Column(name = "status")
    private String status; // SUCCESS, FAILED
    
    // Constructors
    public Transaction() {
    }
    
    public Transaction(String fromAccount, String toAccount, Double amount, 
                       String transactionType, String status) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionType = transactionType;
        this.status = status;
        this.transactionDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFromAccount() {
        return fromAccount;
    }
    
    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
    
    public String getToAccount() {
        return toAccount;
    }
    
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate=" + transactionDate +
                ", status='" + status + '\'' +
                '}';
    }
}

