package com.example.postmantesting.ws;

import com.example.postmantesting.model.Chat;
import com.example.postmantesting.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketHandler extends TextWebSocketHandler {

    @Autowired
    private ChatService chatService;


    private List<WebSocketSession> sessions = new ArrayList<>();

    public void broadcast(String message) {
        try {
            for (WebSocketSession webSession : sessions) { // broadcast
                webSession.sendMessage(new TextMessage(message));
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New session created");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session was removed");
    }
}
