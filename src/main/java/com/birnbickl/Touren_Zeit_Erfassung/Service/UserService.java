package com.birnbickl.Touren_Zeit_Erfassung.Service;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling.UsernameAlreadyExistsException;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerNewUser(String username, String password) {

        if(userRepo.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
        else{
            String encPassword = passwordEncoder.encode(password);
            UserEntity user = new UserEntity(username, encPassword);
            UserEntity savedUser = userRepo.save(user);
            return savedUser;
        }
    }
}
