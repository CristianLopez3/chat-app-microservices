package com.cristian.msconversationsservice.service;

import com.cristian.msconversationsservice.dto.CreateConversationRequestDto;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;

    public Conversation createConversation(CreateConversationRequestDto request) {

        List<UUID> validParticipants = request.participants()
                .stream()
                .map(UUID::fromString)
                .filter(userService::existsByUuid)
                .toList();

        if (validParticipants.size() != request.participants().size()) {
            throw new IllegalArgumentException("One or more participants does not exist");
        }

        Conversation conversation = Conversation.builder()
                .participants(validParticipants)
                .isGroup(request.isGroup())
                .lastMessageAt(null)
                .build();

        return conversationRepository.save(conversation);
    }
}

