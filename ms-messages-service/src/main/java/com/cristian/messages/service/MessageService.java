package com.cristian.messages.service;

import com.cristian.messages.dto.RequestMessageDto;
import reactor.core.publisher.Mono;


public interface MessageService {

    Mono<RequestMessageDto> saveMessage(RequestMessageDto requestMessageDto);

}
