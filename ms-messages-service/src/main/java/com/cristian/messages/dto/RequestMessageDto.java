package com.cristian.messages.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record RequestMessageDto (

        String id,

        @NotEmpty(message = "Message cannot be empty")
        String content,

        @NotEmpty(message = "Sender id cannot be empty")
        String senderId,

        @NotEmpty(message = "Conversation id cannot be empty")
        String conversationId,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
){}