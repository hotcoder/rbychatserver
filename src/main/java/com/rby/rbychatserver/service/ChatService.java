package com.rby.rbychatserver.service;

import com.rby.rbychatserver.model.ChatMessage;

import java.util.List;

// ChatService.java
public interface ChatService {
    List<ChatMessage> getChatMessages();
    void saveMessage(ChatMessage message);
}
