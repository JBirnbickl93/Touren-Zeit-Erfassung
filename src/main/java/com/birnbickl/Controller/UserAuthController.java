package com.birnbickl.Controller;

import com.birnbickl.DTO.LoginResponseDTO;
import com.birnbickl.DTO.UserLoginDTO;
import com.birnbickl.DTO.UserRegistrationDTO;
import com.birnbickl.Entity.UserEntity;
import com.birnbickl.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller Klasse, in der alle User Angelegenheiten entgegengenommen werden

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    // Methode zur User Registrierung

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserEntity newUser = userService.registerNewUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registration successfully");
    }


    // Methode für User Login

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
