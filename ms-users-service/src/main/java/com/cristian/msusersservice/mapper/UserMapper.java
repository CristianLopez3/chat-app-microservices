package com.cristian.msusersservice.mapper;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.model.User;

public class UserMapper {

    private UserMapper(){}

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    public static User toUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.name())
                .username(userRequestDto.username())
                .password(userRequestDto.password())
                .build();
    }



}
