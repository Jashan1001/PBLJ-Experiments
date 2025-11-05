package com.example.partc.repository;

import com.example.partc.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Account entity operations
 */
@Repository
public class AccountRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void save(Account account) {
        getSession().save(account);
        System.out.println("Account saved: " + account.getAccountNumber());
    }
    
    public Account findById(Long id) {
        return getSession().get(Account.class, id);
    }
    
    public Account findByAccountNumber(String accountNumber) {
        Query<Account> query = getSession().createQuery(
            "FROM Account WHERE accountNumber = :accNum", Account.class);
        query.setParameter("accNum", accountNumber);
        return query.uniqueResult();
    }
    
    public List<Account> findAll() {
        Query<Account> query = getSession().createQuery("FROM Account", Account.class);
        return query.list();
    }
    
    public void update(Account account) {
        getSession().update(account);
        System.out.println("Account updated: " + account.getAccountNumber());
    }
    
    public void delete(Long id) {
        Account account = findById(id);
        if (account != null) {
            getSession().delete(account);
            System.out.println("Account deleted: " + account.getAccountNumber());
        }
    }
}

