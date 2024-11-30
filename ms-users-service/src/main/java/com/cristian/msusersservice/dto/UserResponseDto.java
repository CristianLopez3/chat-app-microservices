package com.cristian.msusersservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDto(

        UUID uuid,
        String name,
        String username

) { }
