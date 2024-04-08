package com.rby.rbychatserver.service;

import com.rby.rbychatserver.dto.UserDTO;
import com.rby.rbychatserver.model.ChatRoom;
import com.rby.rbychatserver.model.ChatUser;
import com.rby.rbychatserver.repository.ChatRoomRepository;
import com.rby.rbychatserver.repository.ChatUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ChatUserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatUser authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean joinChatRoom(Long userId, Long roomId) {
        Optional<ChatUser> optionalUser = userRepository.findById(userId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);

        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            ChatUser user = optionalUser.get();
            ChatRoom chatRoom = optionalChatRoom.get();
            user.setRoom(chatRoom);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        ChatUser user = new ChatUser();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        ChatUser save = userRepository.save(user);

        log.info("New user account created.");
    }
}

