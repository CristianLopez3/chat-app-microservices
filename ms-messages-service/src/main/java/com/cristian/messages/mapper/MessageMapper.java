package com.cristian.messages.mapper;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;

import java.util.UUID;


public final class MessageMapper {

    private MessageMapper() {}

    public static Message toMessage(RequestMessageDto requestMessageDto){
        return Message.builder()
                .content(requestMessageDto.content())
                .senderId(UUID.fromString(requestMessageDto.senderId())) // String to UUID
                .createdAt(requestMessageDto.createdAt())
                .conversationId(requestMessageDto.conversationId())
                .build();
    }

    public static RequestMessageDto toRequestMessageDto(Message message){
        return RequestMessageDto.builder()
                .content(message.getContent())
                .senderId(message.getSenderId().toString()) // UUID to String
                .conversationId(message.getConversationId())
                .createdAt(message.getCreatedAt())
                .build();
    }

}
