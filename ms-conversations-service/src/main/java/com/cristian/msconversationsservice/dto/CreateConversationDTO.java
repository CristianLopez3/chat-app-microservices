package com.cristian.msconversationsservice.dto;

import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.model.GroupMetadata;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public record CreateConversationDTO(
        @NotNull(message = "The isGroup field is required")
        Boolean isGroup,

        @NotEmpty(message = "The participants field is required")
        @Size(min = 2, message = "A conversation must have at least two participants")
        List<UUID> participants,

        String groupName,

        String groupDescription
) {

    public boolean isGroupConversation() {
        return Boolean.TRUE.equals(isGroup);
    }

    public Conversation toConversation() {
        return Conversation.builder()
                .isGroup(isGroup)
                .groupMetadata(isGroup
                        ? GroupMetadata.builder()
                            .name(groupName)
                            .description(groupDescription)
                            .build()
                        : null
                ).build();
    }

}
