package com.ava.Networking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ava.Networking.Model.User;
import com.ava.Networking.Model.Student;
import com.ava.Networking.Model.Company;
import com.ava.Networking.Repository.CompanyRepository;
import com.ava.Networking.Repository.StudentRepository;
import com.ava.Networking.Repository.UserRepository;
import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private StudentRepository studentRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
    

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Transactional
    public User registerUser(String email, String password, String userType) {
        logger.info("Attempting to register user with email: {}", email);

        if (userRepository.findByEmail(email) != null) {
            logger.warn("Registration failed: Email already exists: {}", email);
            throw new RuntimeException("Email already exists");
        }

        User user;
        if ("student".equals(userType)) {
            user = new Student();
        } else if ("company".equals(userType)) {
            user = new Company();
        } else {
            throw new RuntimeException("Invalid user type");
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCreatedAt(LocalDateTime.now());

        try {
            User savedUser = userRepository.save(user);
            logger.info("User registered successfully: {}", savedUser.getEmail());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error occurred while saving user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to register user", e);
        }
    }

    public User loginUser(String email, String password) {
        logger.info("Attempting to login user with email: {}", email);
        User user = userRepository.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            logger.info("User logged in successfully: {}", email);
            return user;
        }
        logger.warn("Login failed for email: {}", email);
        return null;
    }
    
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public void deleteUser(Long userId) {
        logger.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
    }

    public User findByUsername(String username) {
        logger.info("Attempting to find user by username: {}", username);
        
        // First, try to find a student with this name
        Student student = studentRepository.findByName(username);
        if (student != null) {
            logger.info("Found student with username: {}", username);
            return student;
        }
        
        // If not found, try to find a company with this name
        Company company = companyRepository.findByName(username);
        if (company != null) {
            logger.info("Found company with username: {}", username);
            return company;
        }
        
        logger.warn("No user found with username: {}", username);
        return null;
    }


}