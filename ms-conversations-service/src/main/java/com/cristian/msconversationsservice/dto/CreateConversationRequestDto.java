package com.cristian.msconversationsservice.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateConversationRequestDto(
        List<UUID> participants,
        boolean isGroup
) {}
