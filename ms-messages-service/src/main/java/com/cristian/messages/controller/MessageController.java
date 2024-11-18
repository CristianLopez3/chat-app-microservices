package com.cristian.messages.controller;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    Mono<RequestMessageDto> saveMessage(@RequestBody RequestMessageDto requestMessageDto) {
        return messageService.saveMessage(requestMessageDto);
    }

}
