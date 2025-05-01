package com.birnbickl.Touren_Zeit_Erfassung.Controller;

import com.birnbickl.Touren_Zeit_Erfassung.DTO.LoginResponseDTO;
import com.birnbickl.Touren_Zeit_Erfassung.DTO.UserLoginDTO;
import com.birnbickl.Touren_Zeit_Erfassung.DTO.UserRegistrationDTO;
import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserEntity newUser = userService.registerNewUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registration successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
