package com.eimantasgag.learning_springboot.chatrooms_db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eimantasgag.learning_springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
