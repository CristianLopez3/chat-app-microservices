package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

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
        // todo - validate username before creating
        var user = UserMapper.toUser(userRequest);
        var savedUser = userRepository.save(user);
        logger.info("User created with id: {}", savedUser.getId());

        return UserMapper.toUserResponseDto(savedUser);
    }


    @Override
    public void update(Long id, UserRequestDto userRequestDto) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id" + id + " not found"));
        user.setName(userRequestDto.name());
        user.setUsername(userRequestDto.username());

        userRepository.save(user);
        logger.debug("Update user: {}", user);

    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return userRepository.existsByUuid(uuid);
    }


}
