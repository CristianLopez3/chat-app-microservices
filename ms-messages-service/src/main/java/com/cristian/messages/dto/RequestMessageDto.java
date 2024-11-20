package com.cristian.messages.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RequestMessageDto (
        String message,
        String sender,
        String receiver,
        LocalDateTime sendDate
){}
