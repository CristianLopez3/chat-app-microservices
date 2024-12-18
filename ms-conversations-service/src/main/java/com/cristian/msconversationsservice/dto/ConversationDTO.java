package com.cristian.msconversationsservice.dto;

import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.model.Participant;
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
){

    public static ConversationDTO fromConversation(Conversation conversation){
        return ConversationDTO.builder()
                .id(conversation.getId())
                .participants(conversation.getParticipants().stream().map(Participant::getParticipantId).toList())
                .isGroup(conversation.isGroup())
                .lastMessageAt(conversation.getLastMessageAt())
                .build();
    }

}