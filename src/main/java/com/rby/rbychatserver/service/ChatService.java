package com.rby.rbychatserver.service;

import com.rby.rbychatserver.model.ChatMessage;
import com.rby.rbychatserver.model.ChatRoom;

import java.util.List;


public interface ChatService {
    List<ChatMessage> getChatMessages(Long roomId);
    void saveMessage(ChatMessage message);

    List<ChatRoom> findAllChatRooms();
}
