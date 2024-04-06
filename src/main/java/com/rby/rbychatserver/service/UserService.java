package com.rby.rbychatserver.service;

import com.rby.rbychatserver.model.User;

// UserService.java
public interface UserService {
    User authenticate(String username, String password);
    boolean joinChatRoom(Long userId, Long roomId);
}
