package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.CreateConversationDto;
import org.springframework.stereotype.Component;

@Component
public class ConversationValidator {

    public void validate(CreateConversationDto dto) {
        if (dto.isGroupConversation()) {
            validateGroupConversation(dto);
        } else {
            validateDirectConversation(dto);
        }
    }

    private void validateGroupConversation(CreateConversationDto dto) {
        if (dto.participants().size() < 2) {
            throw new IllegalArgumentException("A group conversation must have at least two participants.");
        }
        if (dto.groupName() == null || dto.groupName().isBlank()) {
            throw new IllegalArgumentException("The groups must have a name.");
        }
    }

    private void validateDirectConversation(CreateConversationDto dto) {
        if (dto.participants().size() != 2) {
            throw new IllegalArgumentException("An individual conversation must have just two participants.");
        }
        if (dto.groupName() != null || dto.groupDescription() != null) {
            throw new IllegalArgumentException("Individual conversations should not have group metadata.");
        }
    }
}
