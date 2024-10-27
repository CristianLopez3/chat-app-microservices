package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        String path
) {
}
