package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(

        long userId,
        String name,
        String lastname,
        String username

) {
}
