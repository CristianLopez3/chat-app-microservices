package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("TEST: user service implementation")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .username("john.doe@example.com")
                .password("example")
                .build();
    }

    @Test
    void get() {
        given(userRepository.findById(anyLong())).willReturn(Optional.of(mockUser));
        UserResponseDto result = userServiceImpl.get(1L);

        assertNotNull(result);
        then(userRepository).should().findById(anyLong());
    }

    @Test
    void getAll() {
        List<User> userList = List.of(mockUser);

        given(userRepository.findAll()).willReturn(userList);

        List<UserResponseDto> result = userServiceImpl.getAll();

        assertNotNull(result);

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}