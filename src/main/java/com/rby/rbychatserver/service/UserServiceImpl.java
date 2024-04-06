package com.rby.rbychatserver.service;

import com.rby.rbychatserver.model.ChatRoom;
import com.rby.rbychatserver.model.User;
import com.rby.rbychatserver.repository.ChatRoomRepository;
import com.rby.rbychatserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// UserServiceImpl.java
// UserServiceImpl.java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean joinChatRoom(Long userId, Long roomId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);

        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            User user = optionalUser.get();
            ChatRoom chatRoom = optionalChatRoom.get();
            user.setChatRoom(chatRoom);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}

