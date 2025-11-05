package com.example.parta.service;

import com.example.parta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that demonstrates Setter-based Dependency Injection
 */
@Service
public class UserManagerService {
    
    private UserService userService;
    private NotificationService notificationService;
    
    public UserManagerService() {
        System.out.println("UserManagerService bean created!");
    }
    
    // Setter-based Dependency Injection
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
        System.out.println("UserService dependency injected via setter!");
    }
    
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
        System.out.println("NotificationService dependency injected via setter!");
    }
    
    public void registerUser(User user) {
        System.out.println("\n=== UserManagerService: Registering new user ===");
        userService.createUser(user);
        notificationService.sendNotification("New user registered: " + user.getName());
        notificationService.sendEmail(user.getEmail(), "Welcome to our platform!");
    }
    
    public void removeUser(Long userId) {
        System.out.println("\n=== UserManagerService: Removing user ===");
        userService.deleteUser(userId);
        notificationService.sendNotification("User removed with ID: " + userId);
    }
}

