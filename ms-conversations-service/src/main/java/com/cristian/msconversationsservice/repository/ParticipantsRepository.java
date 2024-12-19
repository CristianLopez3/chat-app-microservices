package com.cristian.msconversationsservice.repository;

import com.cristian.msconversationsservice.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participant, Long> {

    @Query("""
            SELECT p.participantId
            FROM Participant p
            WHERE p.conversation.id = :conversationId
    """)
    List<UUID> findParticipantsByConversationId(@Param("conversationId") Long conversationId);

}
