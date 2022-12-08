package com.eimantasgag.learning_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
 
@Entity
@Table(name = "chatrooms")
public class Chatroom{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    @Column(columnDefinition = "VARCHAR(200)[]")
    @Type(type = "com.eimantasgag.learning_springboot.chatrooms_db.SqlStringArrayType")
    private String[] messages;

    public Chatroom(){}
    
    public Chatroom(Integer id, String name, String[] messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getMessages() {
        return messages;
    }
    public void setMessages(String[] messages) {
        this.messages = messages;
    }
    public Integer getId() {
        return id;
    }
}
