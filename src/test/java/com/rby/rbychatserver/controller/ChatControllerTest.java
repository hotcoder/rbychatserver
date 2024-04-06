package com.rby.rbychatserver.controller;

import com.rby.rbychatserver.model.ChatMessage;
import com.rby.rbychatserver.service.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @Test
    public void testGetMessages() throws Exception {
        ChatMessage message = new ChatMessage();
        List<ChatMessage> messages = Collections.singletonList(message);
        given(chatService.getChatMessages()).willReturn(messages);

        mockMvc.perform(get("/api/chat/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testSendMessage() throws Exception {
        mockMvc.perform(post("/api/chat/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"message\": \"Test message\" }"))
                .andExpect(status().isOk());

        verify(chatService, times(1)).saveMessage(any());
    }
}
