package com.cristian.msconversationsservice.service;

import com.cristian.msconversationsservice.dto.CreateConversationRequestDto;
import com.cristian.msconversationsservice.model.Conversation;

public interface ConversationService {

    Conversation createConversation(CreateConversationRequestDto request);

    void deleteConversation(Long conversationId);

}
