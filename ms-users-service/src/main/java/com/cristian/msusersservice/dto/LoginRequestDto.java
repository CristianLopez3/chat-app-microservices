package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record LoginRequestDto(
        String username,
        String password
) {
}
