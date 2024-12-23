package com.cristian.msconversationsservice.service;

import com.cristian.msconversationsservice.dto.ConversationDTO;
import com.cristian.msconversationsservice.dto.ConversationResponseDTO;
import com.cristian.msconversationsservice.dto.CreateConversationDTO;
import com.cristian.msconversationsservice.model.Conversation;

import java.util.List;

public interface ConversationService {


    ConversationDTO createConversation(CreateConversationDTO request);

    void deleteConversation(Long conversationId);

    List<ConversationResponseDTO> getConversationsByUserUUID(String userUUID);

    List<Conversation> getConversations();

}
