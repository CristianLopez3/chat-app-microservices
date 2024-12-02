package com.cristian.msconversationsservice.dto;

import com.cristian.msconversationsservice.model.Conversation;
import lombok.Builder;

import java.util.List;
import java.util.UUID;


@Builder
public record CreateConversationRequestDto(
        List<String> participants,
        boolean isGroup
) {

    Conversation toConversation(){
        return Conversation.builder()
                .participants(participants.stream().map(UUID::fromString).toList())
                .isGroup(isGroup)
                .build();
    }

}
