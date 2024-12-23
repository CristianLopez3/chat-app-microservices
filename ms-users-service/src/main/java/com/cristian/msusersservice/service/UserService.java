package com.cristian.msusersservice.service;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto create(UserRequestDto userRequest);

    UserResponseDto getByUUID(String id);

    List<UserResponseDto> getAll();

    void update(Long id, UserRequestDto userRequestDto);

    boolean existsByUuid(UUID uuid);

}
