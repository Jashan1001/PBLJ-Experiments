package com.example.parta.repository;

import com.example.parta.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for User data access operations
 */
@Repository
public class UserRepository {
    
    private List<User> users = new ArrayList<>();
    
    public UserRepository() {
        System.out.println("UserRepository bean created!");
    }
    
    public void save(User user) {
        users.add(user);
        System.out.println("User saved: " + user.getName());
    }
    
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
    
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
        System.out.println("User deleted with ID: " + id);
    }
}

