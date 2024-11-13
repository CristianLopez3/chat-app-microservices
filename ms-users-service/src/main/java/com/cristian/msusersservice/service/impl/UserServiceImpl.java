package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.AuthService;
import com.cristian.msusersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, AuthService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserResponseDto get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with id: %s, does not exists", id))
                );
        return UserMapper.toUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        var users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequest) {
        var user = UserMapper.toUser(userRequest);
        var savedUser = userRepository.save(user);
        logger.info("User created with id: {}", savedUser.getId());

        return UserMapper.toUserResponseDto(savedUser);
    }


    @Override
    public void update(Long id, UserRequestDto userRequestDto) {

    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        var user = userRepository.findByUsername(loginRequestDto.username())
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with username: %s, does not exists",
                                loginRequestDto.username())));
        return UserMapper.toUserResponseDto(user);
    }


}
