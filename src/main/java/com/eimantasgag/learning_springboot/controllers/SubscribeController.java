package com.eimantasgag.learning_springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.eimantasgag.learning_springboot.databases.ChatroomRepository;
import com.eimantasgag.learning_springboot.model.Chatroom;

@Controller
public class SubscribeController {
    
    @Autowired
    private ChatroomRepository chatroomRepository;

    @SubscribeMapping("/chatroom/{room_name}")
    public String subscribe(@DestinationVariable String room_name){
        String outputMessage = "Connected to " + room_name + " chatroom";

        Optional<Chatroom> chatroom = chatroomRepository.findByName(room_name);

        //if chatroom doesnt exist
        if(!chatroom.isPresent()){
            return "";
        }

        //if exists print out the messages
        String[] messages_arr = chatroom.get().getMessages();

        for(int i = 0;i<messages_arr.length;i++){
            outputMessage += '\n';
            outputMessage += messages_arr[i];
        }

        return outputMessage;
    }
}
