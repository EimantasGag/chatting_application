package com.eimantasgag.learning_springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class ChatUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    private String hashpassword;

    public ChatUser(){}
    
    public ChatUser(String username, String hashPassword) {
        this.username = username;
        this.hashpassword = hashPassword;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashpassword() {
        return hashpassword;
    }
    public void setHashpassword(String hashPassword) {
        this.hashpassword = hashPassword;
    }
}
