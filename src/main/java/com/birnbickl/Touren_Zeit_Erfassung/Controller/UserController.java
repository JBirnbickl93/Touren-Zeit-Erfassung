package com.birnbickl.Touren_Zeit_Erfassung.Controller;

import com.birnbickl.Touren_Zeit_Erfassung.DTO.UserDTO;
import com.birnbickl.Touren_Zeit_Erfassung.Repository.UserRepository;
import com.birnbickl.Touren_Zeit_Erfassung.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}



