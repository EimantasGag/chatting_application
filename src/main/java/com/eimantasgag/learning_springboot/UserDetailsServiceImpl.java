package com.eimantasgag.learning_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eimantasgag.learning_springboot.databases.UserRepository;
import com.eimantasgag.learning_springboot.model.ChatUser;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser chatuser;

        if(userRepository.findByUsername(username).isEmpty()){
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        else{
            chatuser = userRepository.findByUsername(username).get();
        }

        UserDetails user1 = User.withUsername(chatuser.getUsername())
        .password(chatuser.getHashpassword())
        .roles("USER")
        .build();

        return user1;
        
    }
    
}
