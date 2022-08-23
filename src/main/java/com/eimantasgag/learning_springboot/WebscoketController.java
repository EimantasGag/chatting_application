package com.eimantasgag.learning_springboot;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//@Controller
public class WebscoketController {
    
    @MessageMapping("/chatroom/{chatroom_name}")
    @SendTo("/chatroom/{chatroom_name}")
    public String handleMessage(@DestinationVariable String chatroom_name){
        return new String("YOU SENT A MESSAGE TO " + chatroom_name + " CHATROOM");
    }
}
