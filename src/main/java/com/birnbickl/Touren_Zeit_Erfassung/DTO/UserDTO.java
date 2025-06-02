package com.birnbickl.Touren_Zeit_Erfassung.DTO;


public class UserDTO {
    private final long id;
    private final String username;
    private String role;

    public UserDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
