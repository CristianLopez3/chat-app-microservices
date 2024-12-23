package com.cristian.messages.service.impl;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.exception.ResourceNotFoundException;
import com.cristian.messages.mapper.MessageMapper;
import com.cristian.messages.model.Message;
import com.cristian.messages.repository.MessageRepository;
import com.cristian.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public Mono<RequestMessageDto> saveMessage(RequestMessageDto requestMessageDto) {
        return Mono.just(requestMessageDto)
                .map(MessageMapper::toMessage)
                .map(message -> {
                    message.setCreatedAt(LocalDateTime.now());
                    return message;
                })
                .flatMap(messageRepository::save)
                .map(MessageMapper::toRequestMessageDto);
    }

    @Override
    public Flux<Message> getAll(){
        return messageRepository.findAll();
    }

    @Override
    public Flux<Message> getMessagesByConversationId(String conversationId) {
        return messageRepository.findByConversationId(conversationId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No messages found for conversation with id: " + conversationId)))
                .onErrorResume(e -> {
                    logger.error("Error fetching messages for conversationId: {}", conversationId, e);
                    return Mono.error(e);
                });
    }



}
