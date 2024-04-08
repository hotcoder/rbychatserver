package com.rby.rbychatserver.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rby.rbychatserver.model.ChatMessage;
import com.rby.rbychatserver.service.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatController chatController;

    private MockMvc mockMvc;

    @Test
    public void testGetMessagesByRoomId() throws Exception {
        // Mocking chat messages
        ChatMessage message1 = new ChatMessage();
        message1.setId(1L);
        message1.setMessage("Hello from user 1");

        ChatMessage message2 = new ChatMessage();
        message2.setId(2L);
        message2.setMessage("Hi there!");

        List<ChatMessage> messages = Arrays.asList(message1, message2);

        // Mocking service behavior
        when(chatService.getChatMessages(anyLong())).thenReturn(messages);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/chat/rooms/1/messages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].message").value("Hello from user 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].message").value("Hi there!"));

        // Verify service method invocation
        verify(chatService, times(1)).getChatMessages(1L);
    }

    @Test
    public void testSendMessage() throws Exception {
        // Mocking service behavior
        doNothing().when(chatService).saveMessage(any());

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();

        // Prepare JSON payload for the request
        ChatMessage message = new ChatMessage();
        message.setMessage("Test message");

        // Perform POST request and verify the response
        mockMvc.perform(post("/api/chat/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(message)))
                .andExpect(status().isOk());

        // Verify service method invocation
        verify(chatService, times(1)).saveMessage(any());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
