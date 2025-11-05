package com.example.partc.repository;

import com.example.partc.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Transaction entity operations
 */
@Repository
public class TransactionRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void save(Transaction transaction) {
        getSession().save(transaction);
        System.out.println("Transaction logged: " + transaction.getTransactionType());
    }
    
    public Transaction findById(Long id) {
        return getSession().get(Transaction.class, id);
    }
    
    public List<Transaction> findAll() {
        Query<Transaction> query = getSession().createQuery(
            "FROM Transaction ORDER BY transactionDate DESC", Transaction.class);
        return query.list();
    }
    
    public List<Transaction> findByAccount(String accountNumber) {
        Query<Transaction> query = getSession().createQuery(
            "FROM Transaction WHERE fromAccount = :accNum OR toAccount = :accNum " +
            "ORDER BY transactionDate DESC", Transaction.class);
        query.setParameter("accNum", accountNumber);
        return query.list();
    }
}

