package com.birnbickl.Touren_Zeit_Erfassung.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Klasse um beim Login mit DTOs zu arbeiten

public class UserLoginDTO {

    @NotBlank(message = "Username darf nicht leer sein!")
    private String username;
    @NotBlank(message = "Password darf nicht leer sein!")
    @Size(min = 8, message = "Password muss mindestens 8 Zeichen lang sein!")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
