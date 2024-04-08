package com.rby.rbychatserver.controller;

// UserController.java

import com.rby.rbychatserver.dto.UserDTO;
import com.rby.rbychatserver.model.ChatUser;
import com.rby.rbychatserver.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ChatUser> login(@RequestBody ChatUser user) {
        ChatUser authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            log.info("User {} logged in successfully", authenticatedUser.getUsername());
            return ResponseEntity.ok(authenticatedUser);
        } else {
            log.error("User {} failed to login", user.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{userId}/join/{roomId}")
    public ResponseEntity<?> joinChatRoom(@PathVariable Long userId, @PathVariable Long roomId) {
        boolean success = userService.joinChatRoom(userId, roomId);
        if (success) {
            log.info("User {} joined chat room {}", userId, roomId);
            return ResponseEntity.ok("successfully joined chat room");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        // Check if username already exists
        if (userService.isUserExists(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Add new user
        userService.addUser(userDTO);

        return ResponseEntity.ok("User added successfully");
    }
}

