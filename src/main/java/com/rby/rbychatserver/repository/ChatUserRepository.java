package com.rby.rbychatserver.repository;

import com.rby.rbychatserver.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// UserRepository.java
@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    ChatUser findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
