package com.cristian.messages.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RequestMessageDto (

        @NotEmpty(message = "Message cannot be empty")
        String message,

        @NotEmpty(message = "Sender id cannot be empty")
        String senderId,

        @NotEmpty(message = "Receiver id cannot be empty")
        String receiverId,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime sendDate
){}