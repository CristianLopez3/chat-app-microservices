package com.cristian.msusersservice.service;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto create(UserRequestDto userRequest);

    UserResponseDto get(Long id);

    List<UserResponseDto> getAll();

    void update(Long id, UserRequestDto userRequestDto);

}
