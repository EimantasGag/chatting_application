package com.eimantasgag.learning_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eimantasgag.learning_springboot.databases.ChatroomRepository;
import com.eimantasgag.learning_springboot.model.Chatroom;
import java.util.Optional;

@Service
public class ChatroomsService {
    
    @Autowired
    private ChatroomRepository chatroomRepository;

    public Optional<Chatroom> findChatroom(String roomName){
        return chatroomRepository.findByName(roomName);
    }
}
