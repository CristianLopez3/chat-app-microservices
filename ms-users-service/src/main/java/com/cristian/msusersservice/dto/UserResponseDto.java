package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(

        String name,
        String lastname,
        String username

) {
}
