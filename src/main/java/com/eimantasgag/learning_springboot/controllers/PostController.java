package com.eimantasgag.learning_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.eimantasgag.learning_springboot.model.RegisterData;
import com.eimantasgag.learning_springboot.model.RegisterResponse;
import com.eimantasgag.learning_springboot.services.RegisterService;

@RestController
public class PostController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterData payload){
        return registerService.register(payload.getUsername(), payload.getPassword(),payload.getRepeatPassword());
    }
}
