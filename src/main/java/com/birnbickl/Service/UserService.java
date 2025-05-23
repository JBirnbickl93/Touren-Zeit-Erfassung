package com.birnbickl.Service;

import com.birnbickl.Entity.UserEntity;
import com.birnbickl.Enum.Role;
import com.birnbickl.ErrorHandling.InvalidCredentialsException;
import com.birnbickl.ErrorHandling.UsernameAlreadyExistsException;
import com.birnbickl.Repository.UserRepository;
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

    // Methode um einen neuen User zu registrieren
    public UserEntity registerNewUser(String username, String password) {

        if (userRepo.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        } else {
            String encPassword = passwordEncoder.encode(password);
            UserEntity user = new UserEntity(username, encPassword);
            user.setRole(Role.USER);
            UserEntity savedUser = userRepo.save(user);
            return savedUser;
        }
    }

    // Methode um einen User einzuloggen
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

