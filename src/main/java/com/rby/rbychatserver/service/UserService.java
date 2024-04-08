package com.rby.rbychatserver.service;

import com.rby.rbychatserver.dto.UserDTO;
import com.rby.rbychatserver.model.ChatUser;

// UserService.java
public interface UserService {
    ChatUser authenticate(String username, String password);
    boolean joinChatRoom(Long userId, Long roomId);

    boolean isUserExists(String username);

    void addUser(UserDTO userDTO);
}
