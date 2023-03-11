package com.eimantasgag.learning_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eimantasgag.learning_springboot.databases.UserRepository;
import com.eimantasgag.learning_springboot.model.RegisterData;
import com.eimantasgag.learning_springboot.model.RegisterResponse;
import com.eimantasgag.learning_springboot.model.ChatUser;

@RestController
public class PostController {

    @Autowired
    private UserRepository userRepository;

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterData payload){
        
        try{
            if(!payload.getPassword().equals(payload.getRepeatPassword())){
                return new RegisterResponse(400, "Passwords do not match");
            }
            if(userRepository.findByUsername(payload.getUsername()).isPresent()){
                return new RegisterResponse(400, "Username is taken");
            }
            if(payload.getPassword().length() < 8){
                return new RegisterResponse(400, "Password has to be longer than 8 charakters");
            }
            if(payload.getPassword().equals(payload.getUsername())){
                return new RegisterResponse(400, "Password cant be the same as username");
            }

            System.out.println("username: " + payload.getUsername());
        
            String hash = encoder.encode(payload.getPassword()); 
            System.out.println("hashpassword: " + hash);
            
            userRepository.save(new ChatUser(payload.getUsername(), hash));
            return new RegisterResponse(500, "Registration successful");
        
        }
        catch(Exception e){
            return new RegisterResponse(400, "An Error occured. Please try again later");
        }
    }
}
