package com.birnbickl.Touren_Zeit_Erfassung;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Enum.Role;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("user").isEmpty()){
            UserEntity userDummy = new UserEntity("user", passwordEncoder.encode("user12345"), Role.USER);
            userRepository.save(userDummy);
            System.out.println("UserDummy ist angelegt.");
        }
        else {
            System.out.println("User ist bereits angelegt.");
        }
    }
}
