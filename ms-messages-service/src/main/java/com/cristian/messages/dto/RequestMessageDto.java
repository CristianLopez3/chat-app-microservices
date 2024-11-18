package com.cristian.messages.dto;

import lombok.Builder;

@Builder
public record RequestMessageDto (
        String message,
        String sender,
        String receiver
){
}
