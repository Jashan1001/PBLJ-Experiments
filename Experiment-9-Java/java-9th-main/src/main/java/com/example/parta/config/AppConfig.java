package com.example.parta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java-based Configuration for Spring Dependency Injection
 * This replaces XML configuration
 */
@Configuration
@ComponentScan(basePackages = "com.example.parta")
public class AppConfig {
    
    public AppConfig() {
        System.out.println("AppConfig initialized - Java-based Configuration loaded!");
    }
    
    // Beans are automatically detected via @Component, @Service, @Repository annotations
    // due to @ComponentScan
}

