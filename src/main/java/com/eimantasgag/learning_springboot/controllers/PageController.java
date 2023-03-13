package com.eimantasgag.learning_springboot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eimantasgag.learning_springboot.services.ChatroomsService;

@Controller
public class PageController {

    @Autowired
    private ChatroomsService chatroomsService;

    @GetMapping("/chatrooms/{room_name}")
    public String chatroom(@PathVariable String room_name, HttpServletResponse httpServletResponse){

        if(!chatroomsService.findChatroom(room_name).isPresent()){
            System.out.println("room " + room_name + " not found");
            return "chatroom_notfound";
        }
        else{
            System.out.println("room " + room_name + " exists");
        }

        return "chatroom"; // templates/chatroom.html
    }

    @GetMapping("/")
    public String home(HttpServletResponse httpServletResponse){
        return "home"; 
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("got get request to login page");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
