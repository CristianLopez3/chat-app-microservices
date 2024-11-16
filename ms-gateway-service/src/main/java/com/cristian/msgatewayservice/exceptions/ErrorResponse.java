package com.cristian.msgatewayservice.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}