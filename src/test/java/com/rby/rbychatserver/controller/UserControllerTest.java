package com.rby.rbychatserver.controller;


import com.rby.rbychatserver.dto.UserDTO;
import com.rby.rbychatserver.model.User;
import com.rby.rbychatserver.repository.UserRepository;
import com.rby.rbychatserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testLogin_Success() throws Exception {
        User user = new User("testuser", "password");
        given(userService.authenticate(anyString(), anyString())).willReturn(user);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"testuser\", \"password\": \"password\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin_Failure() throws Exception {
        given(userService.authenticate(anyString(), anyString())).willReturn(null);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"testuser\", \"password\": \"password\" }"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testJoinChatRoom_Success() throws Exception {
        given(userService.joinChatRoom(anyLong(), anyLong())).willReturn(true);

        mockMvc.perform(post("/api/users/1/join/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testJoinChatRoom_UserNotFound() throws Exception {
        given(userService.joinChatRoom(anyLong(), anyLong())).willReturn(false);

        mockMvc.perform(post("/api/users/1/join/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("password123");

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());


        userService.addUser(userDTO);

        verify(userRepository, times(1)).save(user);
    }
}

