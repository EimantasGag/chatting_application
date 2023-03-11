package com.eimantasgag.learning_springboot.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.eimantasgag.learning_springboot.model.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    public Optional<Chatroom> findByName(String name);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE chatrooms SET messages = array_append(messages, ?2) WHERE name = ?1", nativeQuery = true)
    public void addMessageToChatroom(String roomName, String message);
}
