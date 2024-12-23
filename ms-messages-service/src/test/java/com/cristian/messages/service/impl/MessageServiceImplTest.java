package com.cristian.messages.service.impl;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.mapper.MessageMapper;
import com.cristian.messages.model.Message;
import com.cristian.messages.repository.MessageRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void saveMessage() {
        var senderUUID = UUID.randomUUID();
        var requestMessageDto = RequestMessageDto.builder()
                .conversationId("1")
                .content("Hi")
                .senderId(senderUUID.toString())
                .build();
        var message = MessageMapper.toMessage(requestMessageDto);
        message.setCreatedAt(LocalDateTime.now());

        Mockito.when(messageRepository.save(any(Message.class))).thenReturn(Mono.just(message));

        Mono<RequestMessageDto> result = messageService.saveMessage(requestMessageDto);

        StepVerifier.create(result)
                .assertNext(savedMessageDto -> {
                    assertNotNull(savedMessageDto);
                    assertEquals(requestMessageDto.conversationId(), savedMessageDto.conversationId());
                    assertEquals(requestMessageDto.content(), savedMessageDto.content());
                    assertEquals(requestMessageDto.senderId(), savedMessageDto.senderId());
                })
                .verifyComplete();
    }

    @Test
    @Disabled("Not implemented yet")
    void getAll() {
    }

    @Test
    @Disabled("Not implemented yet")
    void getMessagesByConversationId() {
    }
}