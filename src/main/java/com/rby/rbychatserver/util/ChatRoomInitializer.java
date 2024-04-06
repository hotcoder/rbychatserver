package com.rby.rbychatserver.util;

import com.rby.rbychatserver.model.ChatRoom;
import com.rby.rbychatserver.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class ChatRoomInitializer implements ApplicationRunner {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomInitializer(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Check if chat room exists
        if (chatRoomRepository.count() == 0) {
            // Create a new chat room
            ChatRoom chatRoom = new ChatRoom();
            // You can set additional properties for the chat room here if needed
            chatRoomRepository.save(chatRoom);
        }
    }
}
