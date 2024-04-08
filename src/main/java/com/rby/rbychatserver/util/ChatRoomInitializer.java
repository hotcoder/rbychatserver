package com.rby.rbychatserver.util;

import com.rby.rbychatserver.model.ChatRoom;
import com.rby.rbychatserver.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ChatRoomInitializer {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomInitializer(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if (chatRoomRepository.count() == 0) {
            // Create a new chat room
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName("General");

            chatRoomRepository.save(chatRoom);
            log.info("Chat room created: {}", chatRoom);
        }
    }
}
