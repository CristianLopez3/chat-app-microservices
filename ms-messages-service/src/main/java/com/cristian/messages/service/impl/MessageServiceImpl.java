package com.cristian.messages.service.impl;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.mapper.MessageMapper;
import com.cristian.messages.model.Message;
import com.cristian.messages.repository.MessageRepository;
import com.cristian.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Mono<RequestMessageDto> saveMessage(RequestMessageDto requestMessageDto) {
        return Mono.just(requestMessageDto)
                .map(MessageMapper::toMessage)
                .map(message -> {
                    message.setSendDate(LocalDateTime.now());
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
    public Mono<List<String>> getUserConversations(String userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId)
                .map(message ->
                        message.getSenderId().equals(userId)
                                ? message.getReceiverId()
                                : message.getSenderId())
                .distinct()
                .collectList();
    }

}
