package com.cristian.msconversationsservice.repository;

import com.cristian.msconversationsservice.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

//    List<Conversation findConversationByParticipantsContains(UUID participant);

}
