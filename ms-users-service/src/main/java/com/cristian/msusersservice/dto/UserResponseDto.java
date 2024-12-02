package com.cristian.msusersservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDto(
        long id,
        UUID uuid,
        String name,
        String username

) { }
