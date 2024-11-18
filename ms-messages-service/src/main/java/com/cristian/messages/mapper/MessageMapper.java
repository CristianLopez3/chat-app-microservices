package com.cristian.messages.mapper;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;


public final class MessageMapper {

    private MessageMapper() {
    }

    public static Message toMessage(RequestMessageDto requestMessageDto){
        return Message.builder()
                .message(requestMessageDto.message())
                .sender(requestMessageDto.sender())
                .receiver(requestMessageDto.receiver())
                .build();
    }

    public static RequestMessageDto toRequestMessageDto(Message message){
        return RequestMessageDto.builder()
                .message(message.getMessage())
                .receiver(message.getReceiver())
                .sender(message.getSender())
                .build();
    }

}
