package com.cristian.msusersservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String message,
        String path,
        int statusCode,
        LocalDateTime localDateTime
) {
}
