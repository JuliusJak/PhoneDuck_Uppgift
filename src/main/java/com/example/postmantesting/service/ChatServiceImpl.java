package com.example.postmantesting.service;

import java.util.List;

import com.example.postmantesting.model.Chat;
import com.example.postmantesting.model.Message;
import com.example.postmantesting.repository.ChatRepository;
import com.example.postmantesting.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(Long id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public void deleteMessage(Long chatID) {
        messageRepository.deleteByChatId(chatID);
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }
}

