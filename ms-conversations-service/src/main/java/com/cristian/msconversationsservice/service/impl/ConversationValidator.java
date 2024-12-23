package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.CreateConversationDTO;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.List;

@Component
public class ConversationValidator {

    private final ConversationRepository conversationRepository;

    public ConversationValidator(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public void validate(CreateConversationDTO dto) {
        if (dto.isGroupConversation()) {
            validateGroupConversation(dto);
        } else {
            validateDirectConversation(dto);
        }
    }

    private void validateGroupConversation(CreateConversationDTO dto) {
        if (dto.participants().size() < 2) {
            throw new IllegalArgumentException("A group conversation must have at least two participants.");
        }
        if (dto.groupName() == null || dto.groupName().isBlank()) {
            throw new IllegalArgumentException("The groups must have a name.");
        }
    }

    private void validateDirectConversation(CreateConversationDTO dto) {
        if (dto.participants().size() != 2) {
            throw new IllegalArgumentException("An individual conversation must have just two participants.");
        }
        if (dto.groupName() != null || dto.groupDescription() != null) {
            throw new IllegalArgumentException("Individual conversations should not have group metadata.");
        }

        // Validate uniqueness of the conversation
        validateNoExistingDirectConversation(dto.participants());
    }

    private void validateNoExistingDirectConversation(List<UUID> participants) {
        UUID participant1 = participants.get(0);
        UUID participant2 = participants.get(1);

        boolean exists = conversationRepository.existsDirectConversation(participant1, participant2);
        if (exists) {
            throw new IllegalArgumentException(
                    "An individual conversation between these participants already exists."
            );
        }
    }
}
