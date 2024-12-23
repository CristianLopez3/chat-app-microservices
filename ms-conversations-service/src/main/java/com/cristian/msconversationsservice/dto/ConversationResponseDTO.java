package com.cristian.msconversationsservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ConversationResponseDTO(
        long id,
        boolean isGroup,
        GroupMetadataResponseDTO groupMetadata,
        List<UserDTO> participants
) { }
