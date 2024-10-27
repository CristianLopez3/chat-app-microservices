package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.model.User;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
        User user = UserMapper.toUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponseDto(savedUser);
    }


    @Override
    public void update(Long id, UserRequestDto userRequestDto) {

    }
}
