package com.eimantasgag.learning_springboot.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    
    @SubscribeMapping("/chatroom/{room_name}")
    public String messages(@DestinationVariable String room_name){
        //TODO: FETCH THE MESSAGES OF room_name
        return "Connected to " + room_name + " chatroom";
    }
}
