package com.cristian.msusersservice.service;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;

public interface AuthService {
    public UserResponseDto login(LoginRequestDto loginRequestDto);
}
