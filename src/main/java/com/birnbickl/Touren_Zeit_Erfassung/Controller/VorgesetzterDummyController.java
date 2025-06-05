package com.birnbickl.Touren_Zeit_Erfassung.Controller;


import com.birnbickl.Touren_Zeit_Erfassung.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vorgesetzter")
public class VorgesetzterDummyController {

    private final UserService userService;
    public VorgesetzterDummyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String helloVorgesetzter(){
        return "Hello Vorgesetzter oder höher!";
    }
}
