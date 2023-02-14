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

    private long identification;
    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody Chat chat) {
        identification++;
        return chatService.createChat(chat);
    }

    @GetMapping("/all")
    public List<Chat> getAllChats() {
        List<Chat> chats = chatService.getAllChats();
        if (chats.isEmpty()) {
            Chat chat = new Chat();
            chat.setTitle("No chats available");
            chats.add(chat);
        }
        return chats;
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
        message.setChatId(id);
        return chatService.createMessage(message);
    }

    @GetMapping("/{id}/messages")
    public List<Message> getMessagesByChatId(@PathVariable Long id) {

        List<Message> messages = chatService.getMessagesByChatId(id);
        Message message = new Message();


        //check if there is a available chat
        if (id > identification) {
            message.setContent("This Chat does not exist");
            messages.add(message);
        }

        //check if there are no messages
        if (messages.isEmpty() && id <= identification) {
            message.setContent("No messages in this chat");
            messages.add(message);
        }
        return messages;
    }

}
