package com.eimantasgag.learning_springboot.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eimantasgag.learning_springboot.model.LoginData;
import com.eimantasgag.learning_springboot.model.LoginResponse;
import com.eimantasgag.learning_springboot.model.RegisterData;
import com.eimantasgag.learning_springboot.model.RegisterResponse;

@RestController
public class PostController {
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginData payload){
        System.out.println("username: " + payload.getUsername());
        System.out.println("password: " + payload.getPassword());
    
        return new LoginResponse(500, "Login successful");
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterData payload){
        System.out.println("username: " + payload.getUsername());
        System.out.println("password: " + payload.getPassword());
    
        return new RegisterResponse(500, "Registration successful");
    }
}
