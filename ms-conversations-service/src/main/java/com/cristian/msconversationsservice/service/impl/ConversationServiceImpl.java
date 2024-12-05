package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.CreateConversationRequestDto;
import com.cristian.msconversationsservice.exception.ResourceNotFoundException;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import com.cristian.msconversationsservice.service.ConversationService;
import com.cristian.msconversationsservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;

    @Override
    public Conversation createConversation(CreateConversationRequestDto request) {
        int participantCount = request.participants().size();

        if (request.isGroup() && participantCount < 2) {
            throw new IllegalArgumentException("Group conversation must have at least 2 participants");
        } else if (!request.isGroup() && participantCount != 2) {
            throw new IllegalArgumentException("Private conversation must have exactly 2 participants");
        }

        List<UUID> validParticipants = request.participants()
                .stream()
                .map(UUID::fromString)
                .filter(userService::existsByUuid)
                .toList();

        if (validParticipants.size() != participantCount) {
            throw new ResourceNotFoundException("One or more participants does not exist");
        }

        Conversation conversation = Conversation.builder()
                .participants(validParticipants)
                .isGroup(request.isGroup())
                .lastMessageAt(null)
                .build();

        return conversationRepository.save(conversation);
    }

    @Override
    public void deleteConversation(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
        conversationRepository.delete(conversation);
    }

    @Override
    public List<Conversation> getConversationsByUserUUID(String userUUID) {
        return List.of();
    }

    @Override
    public List<Conversation> getConversations() {
        return conversationRepository.findAll();
    }


}

