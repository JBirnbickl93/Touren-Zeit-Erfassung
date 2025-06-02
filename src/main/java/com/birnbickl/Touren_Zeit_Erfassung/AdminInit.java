package com.birnbickl.Touren_Zeit_Erfassung;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Enum.Role;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInit {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin12345"));

            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Admin added");
        } else {
            System.out.println("Admin already exists");
        }
    }
}
