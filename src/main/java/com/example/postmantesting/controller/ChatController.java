package com.example.postmantesting.controller;

import java.util.List;

import com.example.postmantesting.model.Chat;
import com.example.postmantesting.model.Message;
import com.example.postmantesting.service.ChatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable Long id) {
        return chatService.getChatById(id);
    }

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @Transactional
    @DeleteMapping("/{id}/chat")
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        deleteMessagesByChatId(id);
    }

    @Transactional
    @DeleteMapping("/messages/{chatId}")
    public void deleteMessagesByChatId(@PathVariable Long chatId) {
        chatService.deleteMessage(chatId);
    }


    @PostMapping("/{id}/messages")
    public Message createMessage(@PathVariable Long id, @RequestBody Message message) {
        //if provided id is higher than the available chat id return error: not valid id
        message.setChatId(id);
        return chatService.createMessage(message);
    }

    @GetMapping("/{id}/messages")
    public List<Message> getMessagesByChatId(@PathVariable Long id) {
        return chatService.getMessagesByChatId(id);
    }
}
