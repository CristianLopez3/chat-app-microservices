package com.cristian.msusersservice.service.impl;

import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.exception.UserNotFoundException;
import com.cristian.msusersservice.mapper.UserMapper;
import com.cristian.msusersservice.repository.UserRepository;
import com.cristian.msusersservice.service.ConversationService;
import com.cristian.msusersservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getUserConversations(long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User With Id: " + userId + " Not Found"));

        var conversations = messageService.getConversationsByUserId(user.getId());

        return userRepository.findAllById(conversations)
                .stream()
                .map(UserMapper::toUserResponseDto)
                .toList();
    }


}
