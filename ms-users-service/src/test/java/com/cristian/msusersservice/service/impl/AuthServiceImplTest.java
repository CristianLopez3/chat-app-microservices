package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {


    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id(1L)
                .name("John")
                .username("john.doe@example.com")
                .password("example")
                .build();
    }

    @Test
    void login() {
        // given
        var loginRequestDto = LoginRequestDto.builder()
                .username(mockUser.getUsername())
                .password(mockUser.getPassword()).build();

        // when
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));

        UserResponseDto result = authService.login(loginRequestDto);

        // then
        verify(userRepository).findByUsername(anyString());
        assertNotNull(result);
        assertThat(result.username(), is("john.doe@example.com"));
    }


    @Test
    void loginFailedWith404() {
        // given
        var loginRequestDto = LoginRequestDto.builder()
                .username(mockUser.getUsername()).build();

        // when
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // then
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            authService.login(loginRequestDto);
        });

        assertEquals("User with username: john.doe@example.com, does not exists", exception.getMessage());
        verify(userRepository).findByUsername(anyString());
    }

}