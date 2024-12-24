package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.ConversationDTO;
import com.cristian.msconversationsservice.dto.ConversationResponseDTO;
import com.cristian.msconversationsservice.dto.CreateConversationDTO;
import com.cristian.msconversationsservice.dto.GroupMetadataResponseDTO;
import com.cristian.msconversationsservice.exception.ResourceNotFoundException;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.model.Participant;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import com.cristian.msconversationsservice.repository.ParticipantsRepository;
import com.cristian.msconversationsservice.service.ConversationService;
import com.cristian.msconversationsservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final Logger logger = Logger.getLogger(ConversationServiceImpl.class);
    private final ConversationRepository conversationRepository;
    private final UserService userService;
    private final ConversationValidator conversationValidator;
    private final ParticipantsRepository participantsRepository;

    @Override
    @Transactional
    public ConversationResponseDTO createConversation(CreateConversationDTO dto) {
        logger.debug("Creating conversation with data: " + dto);
        conversationValidator.validate(dto);

        Conversation conversation = dto.toConversation();

        List<Participant> participants = dto.participants()
                .stream()
                .map(uuid -> {
                    var user = userService.getUserByUUID(uuid.toString());
                    if (user == null) {
                        throw new ResourceNotFoundException("User with UUID " + uuid + " not found");
                    }
                    return Participant.builder()
                            .conversation(conversation)
                            .participantId(uuid)
                            .build();
                }).toList();

        conversation.setParticipants(participants);
        Conversation savedConversation = conversationRepository.save(conversation);
        return ConversationResponseDTO.builder()
                .id(savedConversation.getId())
                .participants(conversation.getParticipants().stream()
                        .map(participant -> userService.getUserByUUID(participant.getParticipantId().toString()))
                        .toList()
                )
                .groupMetadata(savedConversation.getGroupMetadata() != null
                        ? GroupMetadataResponseDTO.builder()
                        .name(savedConversation.getGroupMetadata().getName())
                        .description(savedConversation.getGroupMetadata().getDescription())
                        .build()
                        : null
                )
                .isGroup(savedConversation.isGroup())
                .build();
    }


    @Override
    public void deleteConversation(Long conversationId) {
    }

    @Override
    public List<ConversationResponseDTO> getConversationsByUserUUID(String userUUID) {
        var uuid = UUID.fromString(userUUID);
        var conversations = conversationRepository.findConversationsByUserUuid(uuid);

        return conversations.stream()
                .map(projection -> {
                            var groupMetadata = projection.getIsGroup()
                                    ? GroupMetadataResponseDTO.builder()
                                    .name(projection.getGroupMetadata().getName())
                                    .description(projection.getGroupMetadata().getDescription())
                                    .build()
                                    : null;
                            var participants = participantsRepository.findParticipantsByConversationId(projection.getId())
                                    .stream()
                                    .map(participant -> userService.getUserByUUID(participant.toString()))
                                    .toList();

                            return ConversationResponseDTO.builder()
                                    .id(projection.getId())
                                    .groupMetadata(groupMetadata)
                                    .participants(participants)
                                    .isGroup(projection.getIsGroup())
                                    .build();
                        }

                )
                .toList();
    }

    @Override
    public List<Conversation> getConversations() {
        return List.of();
    }


}

