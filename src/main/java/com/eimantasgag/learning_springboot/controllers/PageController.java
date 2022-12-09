package com.eimantasgag.learning_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eimantasgag.learning_springboot.chatrooms_db.ChatroomRepository;

@Controller
public class PageController {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @GetMapping("/chatrooms/{room_name}")
    public String chatroom(@PathVariable String room_name){
        
        if(chatroomRepository.findByName(room_name) == null){
            System.out.println("room " + room_name + " not found");
            return "chatroom_notfound.html";
        }
        else{
            System.out.println("room " + room_name + " exists");
        }

        return "chatroom"; // templates/chatroom.html
    }

    @GetMapping("/")
    public String index(){
        return "index"; // templates/index.html
    }
}
