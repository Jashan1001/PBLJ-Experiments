# Spring and Hibernate Demonstration Project

This project demonstrates three key Java enterprise concepts:
- **Part A**: Dependency Injection in Spring Using Java-Based Configuration
- **Part B**: Hibernate Application for Student CRUD Operations
- **Part C**: Transaction Management Using Spring and Hibernate

## 📋 Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Part A: Spring Dependency Injection](#part-a-spring-dependency-injection)
- [Part B: Hibernate CRUD Operations](#part-b-hibernate-crud-operations)
- [Part C: Transaction Management](#part-c-transaction-management)
- [Running the Applications](#running-the-applications)
- [Key Concepts Demonstrated](#key-concepts-demonstrated)

## 🔧 Prerequisites

- **JDK 11 or higher**
- **Apache Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

## 📁 Project Structure

```
Java 9th/
├── pom.xml
├── database-setup.sql
├── README.md
├── .gitignore
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           ├── parta/          # Spring DI Demo
        │           │   ├── config/
        │           │   │   └── AppConfig.java
        │           │   ├── model/
        │           │   │   └── User.java
        │           │   ├── repository/
        │           │   │   └── UserRepository.java
        │           │   ├── service/
        │           │   │   ├── UserService.java
        │           │   │   ├── NotificationService.java
        │           │   │   └── UserManagerService.java
        │           │   └── PartAMain.java
        │           │
        │           ├── partb/          # Hibernate CRUD Demo
        │           │   ├── dao/
        │           │   │   ├── StudentDAO.java
        │           │   │   └── StudentDAOImpl.java
        │           │   ├── model/
        │           │   │   └── Student.java
        │           │   ├── util/
        │           │   │   └── HibernateUtil.java
        │           │   └── PartBMain.java
        │           │
        │           └── partc/          # Transaction Management Demo
        │               ├── config/
        │               │   └── HibernateConfig.java
        │               ├── model/
        │               │   ├── Account.java
        │               │   └── Transaction.java
        │               ├── repository/
        │               │   ├── AccountRepository.java
        │               │   └── TransactionRepository.java
        │               ├── service/
        │               │   └── BankingService.java
        │               └── PartCMain.java
        │
        └── resources/
            └── application.properties
```

## 🚀 Setup Instructions

### 1. Clone or Download the Project

```bash
cd "/Users/shivagupta/Java 9th"
```

### 2. Install MySQL and Create Databases

Make sure MySQL is running on your system. Then execute the database setup script:

```bash
mysql -u root -p < database-setup.sql
```

Or manually create the databases:

```sql
CREATE DATABASE student_db;
CREATE DATABASE banking_db;
```

### 3. Configure Database Connection

Update the database credentials in:
- `src/main/resources/application.properties`
- `src/main/java/com/example/partb/util/HibernateUtil.java` (lines 28-30)
- `src/main/java/com/example/partc/config/HibernateConfig.java` (lines 35-37)

**Default credentials:**
- Username: `root`
- Password: `password`
- URLs: 
  - `jdbc:mysql://localhost:3306/student_db`
  - `jdbc:mysql://localhost:3306/banking_db`

### 4. Build the Project

```bash
mvn clean install
```

## 📚 Part A: Spring Dependency Injection

### Overview
Demonstrates Spring Framework's Dependency Injection using **Java-based configuration** (no XML).

### Key Features
- ✅ Java-based configuration using `@Configuration`
- ✅ Component scanning with `@ComponentScan`
- ✅ Constructor-based injection
- ✅ Setter-based injection
- ✅ Service layer architecture

### Classes Involved
- **AppConfig.java**: Java-based Spring configuration
- **UserService.java**: Demonstrates constructor injection
- **UserManagerService.java**: Demonstrates setter injection
- **UserRepository.java**: Data access layer
- **NotificationService.java**: Notification service

### Running Part A

```bash
mvn exec:java -Dexec.mainClass="com.example.parta.PartAMain"
```

**Expected Output:**
- Spring container initialization
- Bean creation messages
- Dependency injection demonstrations
- CRUD operations on User objects

## 📚 Part B: Hibernate CRUD Operations

### Overview
Demonstrates Hibernate ORM for performing CRUD operations on a Student entity.

### Key Features
- ✅ Entity mapping with JPA annotations
- ✅ SessionFactory configuration
- ✅ DAO pattern implementation
- ✅ HQL (Hibernate Query Language)
- ✅ Complete CRUD operations

### Classes Involved
- **Student.java**: Entity class with JPA annotations
- **StudentDAO.java**: Data access interface
- **StudentDAOImpl.java**: DAO implementation with CRUD operations
- **HibernateUtil.java**: SessionFactory management

### Running Part B

```bash
mvn exec:java -Dexec.mainClass="com.example.partb.PartBMain"
```

**Expected Output:**
- SessionFactory creation
- CREATE: Adding students
- READ: Fetching students (all, by ID, by course, by email)
- UPDATE: Modifying student details
- DELETE: Removing students

## 📚 Part C: Transaction Management

### Overview
Demonstrates Spring's declarative transaction management integrated with Hibernate.

### Key Features
- ✅ Spring-Hibernate integration
- ✅ `@Transactional` annotation
- ✅ Declarative transaction management
- ✅ Transaction rollback on exceptions
- ✅ ACID properties demonstration
- ✅ Read-only transactions

### Classes Involved
- **HibernateConfig.java**: Spring-Hibernate configuration with transaction management
- **Account.java**: Account entity
- **Transaction.java**: Transaction log entity
- **AccountRepository.java**: Account data access
- **TransactionRepository.java**: Transaction log data access
- **BankingService.java**: Service with @Transactional methods

### Running Part C

```bash
mvn exec:java -Dexec.mainClass="com.example.partc.PartCMain"
```

**Expected Output:**
- Spring container with transaction management
- Account creation
- Successful transactions (deposit, withdrawal, transfer)
- Failed transaction with automatic rollback
- Transaction history
- Data consistency verification

## 🏃 Running the Applications

### Using Maven

Run each part separately:

```bash
# Part A - Spring Dependency Injection
mvn exec:java -Dexec.mainClass="com.example.parta.PartAMain"

# Part B - Hibernate CRUD
mvn exec:java -Dexec.mainClass="com.example.partb.PartBMain"

# Part C - Transaction Management
mvn exec:java -Dexec.mainClass="com.example.partc.PartCMain"
```

### Using IDE

1. Import the project as a Maven project
2. Wait for dependencies to download
3. Run the main classes:
   - `com.example.parta.PartAMain`
   - `com.example.partb.PartBMain`
   - `com.example.partc.PartCMain`

### Using Command Line (after building)

```bash
# Build first
mvn clean package

# Run Part A
java -cp target/classes:target/dependency/* com.example.parta.PartAMain

# Run Part B
java -cp target/classes:target/dependency/* com.example.partb.PartBMain

# Run Part C
java -cp target/classes:target/dependency/* com.example.partc.PartCMain
```

## 🎯 Key Concepts Demonstrated

### Part A: Spring Dependency Injection

| Concept | Description |
|---------|-------------|
| **Java-based Configuration** | Using `@Configuration` and `@ComponentScan` instead of XML |
| **Constructor Injection** | Dependencies injected via constructor (UserService) |
| **Setter Injection** | Dependencies injected via setter methods (UserManagerService) |
| **Inversion of Control (IoC)** | Spring container manages object lifecycle |
| **Loose Coupling** | Components depend on interfaces, not implementations |

### Part B: Hibernate ORM

| Concept | Description |
|---------|-------------|
| **Object-Relational Mapping** | Java objects mapped to database tables |
| **JPA Annotations** | `@Entity`, `@Table`, `@Column`, `@Id`, etc. |
| **SessionFactory** | Hibernate's factory for database sessions |
| **HQL** | Hibernate Query Language for database queries |
| **CRUD Operations** | Create, Read, Update, Delete operations |

### Part C: Transaction Management

| Concept | Description |
|---------|-------------|
| **@Transactional** | Declarative transaction management |
| **ACID Properties** | Atomicity, Consistency, Isolation, Durability |
| **Transaction Rollback** | Automatic rollback on exceptions |
| **Read-only Transactions** | Optimized transactions for read operations |
| **Atomic Operations** | Multiple operations succeed or fail together |

## 🔍 Transaction Scenarios in Part C

### Scenario 1: Successful Deposit
- Amount added to account
- Balance updated
- Transaction logged
- **Result**: COMMITTED ✅

### Scenario 2: Successful Transfer
- Amount deducted from source account
- Amount added to destination account
- Transaction logged
- **Result**: COMMITTED ✅

### Scenario 3: Failed Transfer (Insufficient Funds)
- Attempt to transfer more than available balance
- Exception thrown
- No changes to any account
- **Result**: ROLLED BACK ↩️

## 📊 Database Tables

### Part B: student_db

**students** table:
- student_id (BIGINT, PRIMARY KEY)
- first_name (VARCHAR)
- last_name (VARCHAR)
- email (VARCHAR, UNIQUE)
- age (INT)
- course (VARCHAR)

### Part C: banking_db

**accounts** table:
- account_id (BIGINT, PRIMARY KEY)
- account_number (VARCHAR, UNIQUE)
- account_holder (VARCHAR)
- balance (DOUBLE)
- account_type (VARCHAR)

**transactions** table:
- transaction_id (BIGINT, PRIMARY KEY)
- from_account (VARCHAR)
- to_account (VARCHAR)
- amount (DOUBLE)
- transaction_type (VARCHAR)
- transaction_date (DATETIME)
- status (VARCHAR)

## 🛠️ Technologies Used

- **Spring Framework 5.3.23** - Dependency Injection & Transaction Management
- **Hibernate 5.6.10** - ORM Framework
- **MySQL 8.0** - Relational Database
- **Maven** - Build & Dependency Management
- **SLF4J** - Logging Framework
- **Apache Commons DBCP2** - Connection Pooling

## 📝 Notes

1. **Database Configuration**: Update credentials before running
2. **Auto-create Tables**: Hibernate will automatically create tables (hbm2ddl.auto=update)
3. **Transaction Isolation**: Default isolation level is used
4. **Connection Pooling**: Configured in HibernateConfig.java
5. **Logging**: SQL statements are logged to console (show_sql=true)

## 🐛 Troubleshooting

### Connection Issues
```
Error: Communications link failure
Solution: Ensure MySQL is running and credentials are correct
```

### ClassNotFoundException
```
Error: ClassNotFoundException
Solution: Run 'mvn clean install' to download dependencies
```

### Port Already in Use
```
Error: Address already in use
Solution: Change MySQL port or stop conflicting services
```

## 📖 Learning Outcomes

After running these applications, you will understand:

1. ✅ How Spring manages dependencies using Java-based configuration
2. ✅ How Hibernate maps Java objects to database tables
3. ✅ How to perform CRUD operations with Hibernate
4. ✅ How Spring manages transactions declaratively
5. ✅ How transaction rollback maintains data consistency
6. ✅ Best practices for enterprise application architecture

## 🤝 Contributing

Feel free to fork this project and add more features:
- Additional Spring concepts (AOP, Events)
- More Hibernate features (Caching, Lazy Loading)
- RESTful API integration
- Unit tests with JUnit and Mockito

## 📄 License

This project is created for educational purposes.

---

**Author**: Java 9th Project
**Date**: November 2025
**Purpose**: Educational demonstration of Spring Framework and Hibernate ORM

---

## 🎓 Additional Resources

- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Spring Transaction Management](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction)
- [JPA Annotations Reference](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)

---

**Happy Coding! 🚀**

