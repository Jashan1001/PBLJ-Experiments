package com.example.parta.service;

import com.example.parta.model.User;
import com.example.parta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for User business logic
 * Demonstrates Constructor Injection
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    // Constructor-based Dependency Injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println("UserService bean created with UserRepository dependency injected!");
    }
    
    public void createUser(User user) {
        System.out.println("UserService: Creating user - " + user.getName());
        userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        System.out.println("UserService: Fetching all users");
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        System.out.println("UserService: Fetching user with ID - " + id);
        return userRepository.findById(id);
    }
    
    public void deleteUser(Long id) {
        System.out.println("UserService: Deleting user with ID - " + id);
        userRepository.deleteById(id);
    }
}

