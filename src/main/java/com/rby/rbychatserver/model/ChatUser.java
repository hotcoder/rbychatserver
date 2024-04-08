package com.rby.rbychatserver.model;

// User.java

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom room;

    public ChatUser() {
    }

    public ChatUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

