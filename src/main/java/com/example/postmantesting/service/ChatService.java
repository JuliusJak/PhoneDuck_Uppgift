package com.example.postmantesting.service;

import com.example.postmantesting.model.Chat;
import com.example.postmantesting.model.Message;

import java.util.List;

public interface ChatService {
    Chat createChat(Chat chat);
    Chat getChatById(Long id);
    List<Chat> getAllChats();
    void deleteChat(Long id);
    void deleteMessage(Long id);
    Message createMessage(Message message);
    List<Message> getMessagesByChatId(Long chatId);
}
