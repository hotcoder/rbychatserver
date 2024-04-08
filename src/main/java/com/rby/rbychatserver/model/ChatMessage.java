package com.rby.rbychatserver.model;

import jakarta.persistence.*;
import lombok.Data;

// ChatMessage.java
@Entity
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    // Other fields and methods
}
