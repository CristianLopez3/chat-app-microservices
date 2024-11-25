package com.cristian.msusersservice.service;

import com.cristian.msusersservice.dto.UserResponseDto;

import java.util.List;

public interface ConversationService {

    List<UserResponseDto> getUserConversations(long userId);

}
