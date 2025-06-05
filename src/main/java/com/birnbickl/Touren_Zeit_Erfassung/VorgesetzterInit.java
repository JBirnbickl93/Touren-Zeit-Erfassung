package com.birnbickl.Touren_Zeit_Erfassung;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Enum.Role;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class VorgesetzterInit {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public VorgesetzterInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("vorgesetzter").isEmpty()) {
            UserEntity vorgesetzter = new UserEntity("vorgesetzter", passwordEncoder.encode("vor123456"), Role.VORGESETZTER);
            userRepository.save(vorgesetzter);
            System.out.println("Vorgesetzter ist angelegt.");
        }
        else {
            System.out.println("Vorgesetzter ist bereits angelegt.");
        }
    }
}
