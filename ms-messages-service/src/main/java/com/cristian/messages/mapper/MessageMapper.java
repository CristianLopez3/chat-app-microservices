package com.cristian.messages.mapper;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;


public final class MessageMapper {

    private MessageMapper() {}

    public static Message toMessage(RequestMessageDto requestMessageDto){
        return Message.builder()
                .message(requestMessageDto.message())
                .senderId(requestMessageDto.senderId())
                .receiverId(requestMessageDto.receiverId())
                .sendDate(requestMessageDto.sendDate())
                .build();
    }

    public static RequestMessageDto toRequestMessageDto(Message message){
        return RequestMessageDto.builder()
                .message(message.getMessage())
                .receiverId(message.getReceiverId())
                .senderId(message.getSenderId())
                .sendDate(message.getSendDate())
                .build();
    }

}
