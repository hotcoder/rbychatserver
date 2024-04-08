package com.rby.rbychatserver.controller;

import com.rby.rbychatserver.model.ChatMessage;
import com.rby.rbychatserver.model.ChatRoom;
import com.rby.rbychatserver.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ChatController.java
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable Long roomId) {
        return chatService.getChatMessages(roomId);
    }

    @PostMapping("/messages")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage message) {
        chatService.saveMessage(message);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/chatrooms")
    public List<ChatRoom> getAllChatRooms() {
        return chatService.findAllChatRooms();
    }


}
