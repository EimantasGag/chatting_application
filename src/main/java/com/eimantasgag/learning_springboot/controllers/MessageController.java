package com.eimantasgag.learning_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.eimantasgag.learning_springboot.chatrooms_db.ChatroomRepository;

@Controller
public class MessageController {
    @Autowired
    private ChatroomRepository chatroomRepository;

    @MessageMapping("/chatroom/{room_name}")
    public void message(@DestinationVariable String room_name, @Payload String payload){
        System.out.println("server received a message: " + payload);
        chatroomRepository.addMessageToChatroom(room_name, payload);
    }
}
