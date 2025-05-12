package com.birnbickl.Touren_Zeit_Erfassung.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Klasse um User als Datenmodell darzustellen

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    @NotBlank(message= "Username darf nicht leer sein!")
    private String username;
    @NotBlank(message = "Password darf nicht leer sein!")
    @Size(min=8, message = "Password muss mindestens 8 Zeichen haben!")
    private String password;
    @Column(nullable = false)
    private String role = "USER";

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
        super();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

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
