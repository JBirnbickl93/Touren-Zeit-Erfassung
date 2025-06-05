package com.birnbickl.Touren_Zeit_Erfassung.Service;

import com.birnbickl.Touren_Zeit_Erfassung.DTO.UserDTO;
import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Enum.Role;
import com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling.InvalidCredentialsException;
import com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling.UsernameAlreadyExistsException;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Diese Klasse beinhaltet alle Methoden um einen User zu registrieren und einzuloggen.
// Außerdem wird sichergestellt, dass der User beim Login ein JWT erhält.

@Service
public class UserService implements UserDetailsService {
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
        return jwtService.generateToken(user);

        }


        public List<UserDTO> getAllUsers() {
            return userRepo.findAll().stream()
                    .map(user-> new UserDTO(user.getId(), user.getUsername(), user.getRole().name()))
                    .toList();
        }



        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            return userRepo.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
        }
    }

