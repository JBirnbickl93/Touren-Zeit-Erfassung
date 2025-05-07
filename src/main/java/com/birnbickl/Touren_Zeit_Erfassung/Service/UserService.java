package com.birnbickl.Touren_Zeit_Erfassung.Service;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling.InvalidCredentialsException;
import com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling.UsernameAlreadyExistsException;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Diese Klasse beinhaltet alle Methoden um einen User zu registrieren und einzuloggen.
// Außerdem wird sichergestellt, dass der User beim Login ein JWT erhält.

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserEntity registerNewUser(String username, String password) {

        if (userRepo.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        } else {
            String encPassword = passwordEncoder.encode(password);
            UserEntity user = new UserEntity(username, encPassword);
            UserEntity savedUser = userRepo.save(user);
            return savedUser;
        }
    }


    public String loginUser(String username, String password) {
        Optional<UserEntity> userOptional = userRepo.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("Invalid Credentials!");
        }

        UserEntity user = userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword()))
        {throw new InvalidCredentialsException("Invalid Credentials!");}
        else{
            return jwtService.generateToken(user);
            }
        }

    }

