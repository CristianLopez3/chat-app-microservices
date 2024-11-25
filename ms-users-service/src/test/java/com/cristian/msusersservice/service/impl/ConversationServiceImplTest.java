package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConversationServiceImplTest {

    @InjectMocks
    private ConversationServiceImpl conversationService;

    @Mock
    private MessageService messageService;

    @Mock
    private UserRepository userRepository;

    private User mockUser1;
    private User mockUser2;

    @BeforeEach
    void setUp() {
        mockUser1 = User.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .username("john.doe@example.com")
                .password("example")
                .build();

        mockUser2 = User.builder()
                .id(2L)
                .name("Jane")
                .lastname("Doe")
                .username("jane.doe@example.com")
                .password("example")
                .build();
    }

    @Test
    void getUserConversations() {
        // given
        long userId = 1L;
        List<Long> conversationIds = List.of(1L, 2L);
        List<User> users = List.of(mockUser1, mockUser2);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser1));
        when(messageService.getConversationsByUserId(userId)).thenReturn(conversationIds);
        when(userRepository.findAllById(conversationIds)).thenReturn(users);

        // when
        List<UserResponseDto> result = conversationService.getUserConversations(userId);

        // then
        verify(messageService).getConversationsByUserId(userId);
        verify(userRepository).findAllById(conversationIds);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockUser1.getId(), result.get(0).userId());
        assertEquals(mockUser2.getId(), result.get(1).userId());
    }
}