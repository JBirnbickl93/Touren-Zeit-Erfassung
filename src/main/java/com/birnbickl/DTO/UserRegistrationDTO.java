package com.birnbickl.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Klasse um bei der Userregistrierung über DTOs zu arbeiten

public class UserRegistrationDTO {

    @NotBlank(message = "Username darf nicht leer sein!")
    private String username;
    @NotBlank(message = "Passwort darf nicht leer sein!")
    @Size(min = 8, message = "Passwort muss mindestens 8 Zeichen lang sein!")
    private String password;


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
