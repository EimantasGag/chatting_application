package com.eimantasgag.learning_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eimantasgag.learning_springboot.chatrooms_db.UserRepository;
import com.eimantasgag.learning_springboot.model.LoginData;
import com.eimantasgag.learning_springboot.model.LoginResponse;
import com.eimantasgag.learning_springboot.model.RegisterData;
import com.eimantasgag.learning_springboot.model.RegisterResponse;
import com.eimantasgag.learning_springboot.model.User;

@RestController
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginData payload){
        System.out.println("username: " + payload.getUsername());
        System.out.println("password: " + payload.getPassword());

        try{
            userRepository.save(new User(payload.getUsername(), payload.getPassword()));
        }
        catch(Exception e){
            return new LoginResponse(400, "An Error occured. Please try again later");
        }
    
        return new LoginResponse(500, "Login successful");
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterData payload){
        System.out.println("username: " + payload.getUsername());
        System.out.println("password: " + payload.getPassword());

        try{
            userRepository.save(new User(payload.getUsername(), payload.getPassword()));
        }
        catch(Exception e){
            return new RegisterResponse(400, "An Error occured. Please try again later");
        }
        
    
        return new RegisterResponse(500, "Registration successful");
    }
}
