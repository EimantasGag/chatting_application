package com.eimantasgag.learning_springboot.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebsocketComponent {
    
    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("connected to websocket");
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
       System.out.println("disconnected from websocket");
    }

}
