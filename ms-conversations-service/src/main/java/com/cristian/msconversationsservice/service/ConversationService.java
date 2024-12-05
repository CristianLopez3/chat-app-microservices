package com.cristian.msconversationsservice.service;

import com.cristian.msconversationsservice.dto.CreateConversationRequestDto;
import com.cristian.msconversationsservice.model.Conversation;

import java.util.List;

public interface ConversationService {

    Conversation createConversation(CreateConversationRequestDto request);

    void deleteConversation(Long conversationId);

    List<Conversation> getConversationsByUserUUID(String userUUID);

    List<Conversation> getConversations();

}
