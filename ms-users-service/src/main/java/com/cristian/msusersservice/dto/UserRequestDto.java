package com.cristian.msusersservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record  UserRequestDto(
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "Username is required")
        String username,
        @NotEmpty(message = "Password is required")
        String password

) { }
