package com.rby.rbychatserver.service;

import com.rby.rbychatserver.model.ChatMessage;
import com.rby.rbychatserver.repository.ChatMessageRepository;
import com.rby.rbychatserver.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ChatServiceImpl.java
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> getChatMessages() {
        return chatMessageRepository.findAll();
    }

    @Override
    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }
}
