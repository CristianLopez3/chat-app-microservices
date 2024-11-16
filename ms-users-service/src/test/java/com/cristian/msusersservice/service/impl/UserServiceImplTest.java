package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Disabled
    void create() {
        UserRequestDto userRequest = UserRequestDto.builder()
                .name("Jhonatan")
                .username("John_natan")
                .build();


        when(userRepository.save(mockUser)).thenReturn(mockUser);

        userServiceImpl.create(userRequest);

        verify(userRepository).save(any(User.class));
    }

    @Test
    void update() {
    }






}