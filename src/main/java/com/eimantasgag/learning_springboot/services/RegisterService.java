package com.eimantasgag.learning_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eimantasgag.learning_springboot.databases.UserRepository;
import com.eimantasgag.learning_springboot.model.ChatUser;
import com.eimantasgag.learning_springboot.model.RegisterResponse;

@Service
public class RegisterService {
    
    @Autowired
    private UserRepository userRepository;
    
    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegisterResponse register(String username, String password, String repeatPassword){

        try{
            if(!password.equals(repeatPassword)){
                return new RegisterResponse(false, "Passwords do not match");
            }
            if(userRepository.findByUsername(username).isPresent()){
                return new RegisterResponse(false, "Username is taken");
            }
            if(password.length() < 8){
                return new RegisterResponse(false, "Password has to be longer than 8 charakters");
            }
            if(password.equals(username)){
                return new RegisterResponse(false, "Password cant be the same as username");
            }
            
            userRepository.save(new ChatUser(username, encoder.encode(password)));

            return new RegisterResponse(true, "Registration successful");
        }
        catch(Exception e){
            return new RegisterResponse(false, "An Error occured. Please try again later");
        }
    }

    
}
