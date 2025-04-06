package com.mycompany.sarpus.service;

import com.mycompany.sarpus.model.User;
import com.mycompany.sarpus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void createAdminUser() {
        if (!userRepository.existsByEmail("admin@sarpus.com")) {
            User adminUser = new User();
            adminUser.setEmail("admin@sarpus.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFullName("Admin User");
            adminUser.setRole("ROLE_ADMIN");
            userRepository.save(adminUser);
        }
    }
}

