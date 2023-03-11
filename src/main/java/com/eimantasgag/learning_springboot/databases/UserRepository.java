package com.eimantasgag.learning_springboot.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eimantasgag.learning_springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
