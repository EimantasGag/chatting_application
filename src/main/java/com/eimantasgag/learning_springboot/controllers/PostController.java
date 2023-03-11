package com.eimantasgag.learning_springboot.controllers;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eimantasgag.learning_springboot.databases.UserRepository;
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
    public LoginResponse login(@RequestBody LoginData payload, HttpServletResponse httpServletResponse){
        System.out.println("username: " + payload.getUsername());
        Optional<User> optionaluser = userRepository.findByUsername(payload.getUsername());

        if(payload.isRememberUser()){
            httpServletResponse.addCookie(new Cookie("username", payload.getUsername()));
        }
        
        //TODO: FIGURE OUT HOW DATA IN COOKIES SHOULD BE STORED SO IT WONT BE MANIPULATED
        //TODO: AFTER LOGIN REDIRECT CLIENT TO THE PAGE HE WANTED INITIALLY

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
                return new LoginResponse(400, "User with name " + payload.getUsername() + " does not exist");
            }
        }
        catch(Exception e){
            return new LoginResponse(400, "Error occured please try again later");
        }
    }

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
            
            userRepository.save(new User(payload.getUsername(), hash));
            return new RegisterResponse(500, "Registration successful");
        
        }
        catch(Exception e){
            return new RegisterResponse(400, "An Error occured. Please try again later");
        }
    }
}
