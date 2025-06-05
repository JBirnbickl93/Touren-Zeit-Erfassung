package com.birnbickl.Touren_Zeit_Erfassung.Controller;


import com.birnbickl.Touren_Zeit_Erfassung.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserDummyController {

    private final UserService userService;
    public UserDummyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String helloUser(){
        return "Hallo User oder höher!";
    }
}
