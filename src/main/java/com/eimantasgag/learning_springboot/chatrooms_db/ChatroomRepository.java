package com.eimantasgag.learning_springboot.chatrooms_db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eimantasgag.learning_springboot.model.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    public Chatroom findByName(String name);
    
    //TODO: RUN A CUSTOM @QUERY HERE
    @Query(value = "UPDATE chatrooms SET messages = array_append(messages, ?2) WHERE name = ?1", nativeQuery = true)
    public void addMessageToChatroom(String roomName, String message);
}
