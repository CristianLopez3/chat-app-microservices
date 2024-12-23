package com.cristian.msconversationsservice.service.impl;

import com.cristian.msconversationsservice.dto.ConversationProjection;
import com.cristian.msconversationsservice.dto.ConversationResponseDTO;
import com.cristian.msconversationsservice.dto.UserDTO;
import com.cristian.msconversationsservice.repository.ConversationRepository;
import com.cristian.msconversationsservice.repository.ParticipantsRepository;
import com.cristian.msconversationsservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversationServiceImplTest {

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private ParticipantsRepository participantsRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConversationServiceImpl conversationService;

    private UUID userUUID;
    private ConversationProjection conversationProjection;
    private List<UUID> participants;

    @BeforeEach
    void setUp() {
        userUUID = UUID.randomUUID();
        participants = List.of(UUID.randomUUID());

        conversationProjection = new ConversationProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public boolean getIsGroup() {
                return true;
            }

            @Override
            public GroupMetadataProjection getGroupMetadata() {
                return new GroupMetadataProjection() {
                    @Override
                    public Long getId() {
                        return 1L;
                    }

                    @Override
                    public String getName() {
                        return "Group Name";
                    }

                    @Override
                    public String getDescription() {
                        return "Group Description";
                    }
                };
            }

        };
    }

    @Test
    @DisplayName("Should return a list of conversations by user UUID")
    void testGetConversationsByUserUUID() {
        when(conversationRepository.findConversationsByUserUuid(any(UUID.class)))
                .thenReturn(List.of(conversationProjection));
        when(participantsRepository.findParticipantsByConversationId(any(Long.class)))
                .thenReturn(participants);
        when(userService.getUserByUUID(anyString()))
                .thenReturn(UserDTO.builder().name("John Doe").build());

        List<ConversationResponseDTO> result = conversationService.getConversationsByUserUUID(userUUID.toString());

        assertEquals(1, result.size());
        ConversationResponseDTO responseDTO = result.getFirst();
        assertEquals(1L, responseDTO.id());
        assertTrue(responseDTO.isGroup());
        assertEquals("Group Name", responseDTO.groupMetadata().name());
        assertEquals("Group Description", responseDTO.groupMetadata().description());
        assertEquals(participants.size(), responseDTO.participants().size());
    }
}