package com.cristian.msconversationsservice.service;

import com.cristian.msconversationsservice.dto.ConversationDTO;
import com.cristian.msconversationsservice.dto.ConversationProjection;
import com.cristian.msconversationsservice.dto.ConversationResponseDTO;
import com.cristian.msconversationsservice.dto.CreateConversationDto;
import com.cristian.msconversationsservice.model.Conversation;

import java.util.List;

public interface ConversationService {


    ConversationDTO createConversation(CreateConversationDto request);

    void deleteConversation(Long conversationId);

    List<ConversationResponseDTO> getConversationsByUserUUID(String userUUID);

    List<Conversation> getConversations();

}
