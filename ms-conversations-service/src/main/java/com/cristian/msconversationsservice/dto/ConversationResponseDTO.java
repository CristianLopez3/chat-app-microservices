package com.cristian.msconversationsservice.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ConversationResponseDTO(
        long id,
        boolean isGroup,
        GroupMetadataResponseDTO groupMetadata,
        List<UUID> participants
) { }
