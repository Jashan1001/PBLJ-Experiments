package com.example.parta.service;

import org.springframework.stereotype.Service;

/**
 * Service class for sending notifications
 * Demonstrates Setter Injection
 */
@Service
public class NotificationService {
    
    public NotificationService() {
        System.out.println("NotificationService bean created!");
    }
    
    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
    
    public void sendEmail(String to, String subject) {
        System.out.println("Email sent to: " + to + " with subject: " + subject);
    }
}

