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

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Mono<RequestMessageDto> saveMessage(RequestMessageDto requestMessageDto) {
        return Mono.just(requestMessageDto)
                .map(MessageMapper::toMessage)
                .flatMap(messageRepository::insert)
                .map(MessageMapper::toRequestMessageDto);
    }

    public Flux<Message> getAll(){
        return messageRepository.findAll();
    }

}
