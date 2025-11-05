package com.example.partc.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Java-based Configuration for Spring and Hibernate Integration
 * with Transaction Management
 * Part C: Transaction Management Using Spring and Hibernate
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.partc")
public class HibernateConfig {
    
    /**
     * Configure DataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/banking_db?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        
        // Connection pool settings
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        
        System.out.println("DataSource configured successfully!");
        return dataSource;
    }
    
    /**
     * Configure Hibernate SessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.partc.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        
        System.out.println("SessionFactory configured successfully!");
        return sessionFactory;
    }
    
    /**
     * Configure Hibernate properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.current_session_context_class", 
            "org.springframework.orm.hibernate5.SpringSessionContext");
        
        return properties;
    }
    
    /**
     * Configure Transaction Manager
     * This enables @Transactional annotation support
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        
        System.out.println("Transaction Manager configured successfully!");
        return transactionManager;
    }
}

