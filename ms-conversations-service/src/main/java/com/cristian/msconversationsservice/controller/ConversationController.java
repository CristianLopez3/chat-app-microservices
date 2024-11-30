package com.cristian.msconversationsservice.controller;

import com.cristian.msconversationsservice.dto.CreateConversationRequestDto;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversations")
@AllArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestBody CreateConversationRequestDto request) {
        Conversation conversation = conversationService.createConversation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
    }


}

