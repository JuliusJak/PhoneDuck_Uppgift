package com.example.postmantesting.repository;

import java.util.List;

import com.example.postmantesting.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatId(Long chatId);
    void deleteByChatId(Long chatId);

}
