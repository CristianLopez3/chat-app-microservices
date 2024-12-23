package com.cristian.msconversationsservice.dto;

import lombok.Builder;

@Builder
public record UserDTO (
        Long id,
        String name,
        String uuid,
        String username
){}
