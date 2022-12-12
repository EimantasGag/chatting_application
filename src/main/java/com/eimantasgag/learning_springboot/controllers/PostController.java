package com.eimantasgag.learning_springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginData payload){
        System.out.println("username: " + payload.getUsername());
        Optional<User> optionaluser = userRepository.findByUsername(payload.getUsername());

        try{
            if(optionaluser.isPresent()){
                User user = optionaluser.get();
    
                if(encoder.matches(payload.getPassword(), user.getHashpassword())){
                    return new LoginResponse(500, "Login successful");
                }
                else{
                    return new LoginResponse(400, "Wrong password");
                }
            }
            else{
                return new LoginResponse(400, "User with name" + payload.getUsername() + " does not exist");
            }
        }
        catch(Exception e){
            return new LoginResponse(400, "Error occured please try again later");
        }
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterData payload){
        System.out.println("username: " + payload.getUsername());
        
        String hash = encoder.encode(payload.getPassword()); 
        System.out.println("hashpassword: " + hash);

        try{
            userRepository.save(new User(payload.getUsername(), hash));
        }
        catch(Exception e){
            return new RegisterResponse(400, "An Error occured. Please try again later");
        }
        
    
        return new RegisterResponse(500, "Registration successful");
    }
}
