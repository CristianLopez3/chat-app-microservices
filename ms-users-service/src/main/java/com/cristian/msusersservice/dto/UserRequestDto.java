package com.cristian.msusersservice.dto;

import lombok.Builder;

@Builder
public record  UserRequestDto(

        String name,
        String lastname,
        String username,
        String password

) { }
