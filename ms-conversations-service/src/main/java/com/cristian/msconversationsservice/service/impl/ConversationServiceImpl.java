package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.ConversationDTO;
import com.cristian.msconversationsservice.dto.CreateConversationDto;
import com.cristian.msconversationsservice.exception.ResourceNotFoundException;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.model.Participant;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import com.cristian.msconversationsservice.service.ConversationService;
import com.cristian.msconversationsservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl  implements ConversationService {

    private final Logger logger = Logger.getLogger(ConversationServiceImpl.class);
    private final ConversationRepository conversationRepository;
    private final UserService userService;
    private final ConversationValidator conversationValidator;

    @Override
    @Transactional
    public ConversationDTO createConversation(CreateConversationDto dto) {
        logger.debug("Creating conversation with data: " + dto);
        conversationValidator.validate(dto);

        Conversation conversation = dto.toConversation();

        List<Participant> participants = dto.participants()
                .stream()
                .map(participantUUID -> {
                    if (!userService.existsByUuid(participantUUID)) {
                        throw new ResourceNotFoundException("User with UUID " + participantUUID + " not found");
                    }
                    return Participant.builder()
                            .participantId(participantUUID)
                            .conversation(conversation)
                            .build();
                }).toList();

        conversation.setParticipants(participants);
        Conversation savedConversation = conversationRepository.save(conversation);
        return ConversationDTO.fromConversation(savedConversation);
    }




    @Override
    public void deleteConversation(Long conversationId) {
    }

    @Override
    public List<Conversation> getConversationsByUserUUID(String userUUID) {
        return List.of();
    }

    @Override
    public List<Conversation> getConversations() {
        return List.of();
    }


}

