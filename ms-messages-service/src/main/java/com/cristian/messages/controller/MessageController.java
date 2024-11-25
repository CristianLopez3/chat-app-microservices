package com.cristian.messages.controller;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;
import com.cristian.messages.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    @PostMapping
    Mono<RequestMessageDto> saveMessage(@RequestBody @Valid RequestMessageDto requestMessageDto) {
        logger.debug("Received request to save message: {}", requestMessageDto);
        return messageService.saveMessage(requestMessageDto);
    }

    @GetMapping
    Flux<Message> getAll(){
        return messageService.getAll();
    }

    @GetMapping("/{userId}/conversations")
    Mono<List<String>> getUserConversations(@PathVariable String userId){
        return messageService.getUserConversations(userId);
    }

}
