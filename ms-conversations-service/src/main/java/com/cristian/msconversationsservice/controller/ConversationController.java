package com.cristian.msconversationsservice.controller;

import com.cristian.msconversationsservice.dto.ConversationDTO;
import com.cristian.msconversationsservice.dto.ConversationResponseDTO;
import com.cristian.msconversationsservice.dto.CreateConversationDTO;
import com.cristian.msconversationsservice.service.ConversationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@AllArgsConstructor
public class ConversationController {

    private final Logger logger = LoggerFactory.getLogger(ConversationController.class);
    private final ConversationService conversationService;

    @PostMapping
    public ResponseEntity<ConversationResponseDTO> createConversation(@RequestBody @Valid CreateConversationDTO request) {
        logger.debug("Creating conversation with data: {}", request);
        var conversation = conversationService.createConversation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
    }

    @GetMapping
    public ResponseEntity<List<ConversationResponseDTO>> getConversationsByUserUUID(
            @RequestParam(name = "user")
            @NotEmpty(message = "User parameter must contains a valid value") String userUUID) {
        logger.debug("Getting conversations for user with UUID: {}", userUUID);
        var conversations = conversationService.getConversationsByUserUUID(userUUID);
        return ResponseEntity.ok(conversations);
    }

}

