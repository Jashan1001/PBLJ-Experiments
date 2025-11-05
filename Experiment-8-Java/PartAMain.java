package com.example.parta;

import com.example.parta.config.AppConfig;
import com.example.parta.model.User;
import com.example.parta.service.UserManagerService;
import com.example.parta.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class to demonstrate Spring Dependency Injection using Java-based Configuration
 * Part A: Dependency Injection in Spring Using Java-Based Configuration
 */
public class PartAMain {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Part A: Spring Dependency Injection Demo");
        System.out.println("Java-Based Configuration");
        System.out.println("========================================\n");
        
        // Create Spring Application Context using Java-based configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        System.out.println("\n========================================");
        System.out.println("Spring Container Initialized Successfully!");
        System.out.println("========================================\n");
        
        // Get beans from Spring container
        UserService userService = context.getBean(UserService.class);
        UserManagerService userManagerService = context.getBean(UserManagerService.class);
        
        // Demonstrate Constructor Injection (UserService)
        System.out.println("\n--- Demonstrating Constructor Injection ---");
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");
        
        userService.createUser(user1);
        userService.createUser(user2);
        
        // Demonstrate Setter Injection (UserManagerService)
        System.out.println("\n--- Demonstrating Setter Injection ---");
        User user3 = new User(3L, "Bob Johnson", "bob@example.com");
        userManagerService.registerUser(user3);
        
        // Display all users
        System.out.println("\n--- All Registered Users ---");
        userService.getAllUsers().forEach(System.out::println);
        
        // Delete a user
        userManagerService.removeUser(2L);
        
        // Display remaining users
        System.out.println("\n--- Remaining Users ---");
        userService.getAllUsers().forEach(System.out::println);
        
        System.out.println("\n========================================");
        System.out.println("Part A Demo Completed Successfully!");
        System.out.println("========================================");
        
        // Close context
        ((AnnotationConfigApplicationContext) context).close();
    }
}

