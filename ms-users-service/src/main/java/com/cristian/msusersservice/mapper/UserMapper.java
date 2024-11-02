package com.cristian.msusersservice.mapper;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.model.User;

public class UserMapper {

    private UserMapper(){}

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .build();
    }

    public static User toUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.name())
                .lastname(userRequestDto.lastname())
                .username(userRequestDto.username())
                .password(userRequestDto.password())
                .build();
    }



}
