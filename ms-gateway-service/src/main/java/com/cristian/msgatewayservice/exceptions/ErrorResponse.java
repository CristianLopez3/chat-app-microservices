package com.cristian.msgatewayservice.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String path,
        String message,
        int statusCode,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime localDateTime
) {
}