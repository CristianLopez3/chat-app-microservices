package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record  UserRequestDto(

        String name,
        String username,
        String password

) { }
