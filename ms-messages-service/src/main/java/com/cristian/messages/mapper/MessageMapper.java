package com.cristian.messages.mapper;

import com.cristian.messages.dto.RequestMessageDto;
import com.cristian.messages.model.Message;


public final class MessageMapper {

    private MessageMapper() {}

    public static Message toMessage(RequestMessageDto requestMessageDto){
        return Message.builder()
                .content(requestMessageDto.content())
                .senderId(requestMessageDto.senderId())
                .createdAt(requestMessageDto.createdAt())
                .conversationId(requestMessageDto.conversationId())
                .build();
    }

    public static RequestMessageDto toRequestMessageDto(Message message){
        return RequestMessageDto.builder()
                .content(message.getContent())
                .senderId(message.getSenderId())
                .conversationId(message.getConversationId())
                .createdAt(message.getCreatedAt())
                .build();
    }

}
