package com.cristian.msconversationsservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ConversationDTO (
    Long id,
    List<UUID> participants,
    boolean isGroup,
    LocalDateTime lastMessageAt
){ }