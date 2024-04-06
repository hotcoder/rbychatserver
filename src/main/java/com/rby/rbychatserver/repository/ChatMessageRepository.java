package com.rby.rbychatserver.repository;

import com.rby.rbychatserver.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ChatMessageRepository.java
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
