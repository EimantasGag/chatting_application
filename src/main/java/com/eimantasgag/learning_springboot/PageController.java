package com.eimantasgag.learning_springboot;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // @MessageMapping("/chatroom/{chatroom_name}")
    // @SendTo("/chatroom/{chatroom_name}")
    // public String handleMessage(@DestinationVariable String chatroom_name){
    //     return new String("YOU SENT A MESSAGE TO " + chatroom_name + " CHATROOM");
    // }

    @GetMapping("/chatrooms/{room}")
    public String chatroom(@DestinationVariable String room){
        return "chatroom";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
