package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        var user = userRepository.findByUsername(loginRequestDto.username())
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with username: %s, does not exists",
                                loginRequestDto.username())));
        return UserMapper.toUserResponseDto(user);
    }
}
