package com.cristian.messages.service;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface MessageService {

    Mono<RequestMessageDto> saveMessage(RequestMessageDto requestMessageDto);

    Flux<Message> getAll();
}
