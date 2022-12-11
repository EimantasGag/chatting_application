package com.eimantasgag.learning_springboot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eimantasgag.learning_springboot.chatrooms_db.ChatroomRepository;

@Controller
public class PageController {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @GetMapping("/chatrooms/{room_name}")
    public String chatroom(@CookieValue(value = "username", defaultValue = "") 
    String username, @PathVariable String room_name,
    HttpServletResponse httpServletResponse){
        
        //user havent logged in or doesnt have an account
        if(username.equals("")){
            try{
                httpServletResponse.sendRedirect("/login");
            }
            catch(Exception e){}
        }

        if(!chatroomRepository.findByName(room_name).isPresent()){
            System.out.println("room " + room_name + " not found");
            return "chatroom_notfound";
        }
        else{
            System.out.println("room " + room_name + " exists");
        }

        return "chatroom"; // templates/chatroom.html
    }

    @GetMapping("/")
    public String home(@CookieValue(value = "username", defaultValue = "") String username, HttpServletResponse httpServletResponse){
        if(username.equals("")){
            try{
                httpServletResponse.sendRedirect("/login");
            }
            catch(Exception e){}
        }
        
        return "home"; // templates/home.html
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
