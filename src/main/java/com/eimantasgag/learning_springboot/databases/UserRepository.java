package com.eimantasgag.learning_springboot.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eimantasgag.learning_springboot.model.ChatUser;

public interface UserRepository extends JpaRepository<ChatUser, Integer> {
    public Optional<ChatUser> findByUsername(String username);
}
