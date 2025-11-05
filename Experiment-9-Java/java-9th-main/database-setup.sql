-- Database Setup Script
-- Run this script before executing the applications

-- Create databases
CREATE DATABASE IF NOT EXISTS student_db;
CREATE DATABASE IF NOT EXISTS banking_db;

-- Use student_db
USE student_db;

-- Students table will be auto-created by Hibernate
-- But here's the structure for reference:
/*
CREATE TABLE IF NOT EXISTS students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT,
    course VARCHAR(100)
);
*/

-- Use banking_db
USE banking_db;

-- Accounts and Transactions tables will be auto-created by Hibernate
-- But here's the structure for reference:
/*
CREATE TABLE IF NOT EXISTS accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_holder VARCHAR(100) NOT NULL,
    balance DOUBLE NOT NULL,
    account_type VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_account VARCHAR(20),
    to_account VARCHAR(20),
    amount DOUBLE NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    transaction_date DATETIME,
    status VARCHAR(20)
);
*/

-- Grant privileges (adjust as needed)
-- GRANT ALL PRIVILEGES ON student_db.* TO 'root'@'localhost';
-- GRANT ALL PRIVILEGES ON banking_db.* TO 'root'@'localhost';
-- FLUSH PRIVILEGES;

SELECT 'Database setup completed!' AS message;

