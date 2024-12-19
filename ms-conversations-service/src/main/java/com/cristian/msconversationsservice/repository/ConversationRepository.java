package com.cristian.msconversationsservice.repository;

import com.cristian.msconversationsservice.dto.ConversationProjection;
import com.cristian.msconversationsservice.model.Conversation;
import com.cristian.msconversationsservice.service.impl.ConversationValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("""
            SELECT c.id AS id,
                   c.isGroup AS isGroup,
                   c.groupMetadata AS groupMetadata
            FROM Conversation c
            LEFT JOIN c.groupMetadata gm
            JOIN c.participants p
            WHERE p.participantId = :userUuid
        """)
    List<ConversationProjection> findConversationsByUserUuid(@Param("userUuid") UUID userUuid);


    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END
            FROM Conversation c 
            JOIN c.participants p1 
            JOIN c.participants p2
            WHERE c.isGroup = FALSE 
            AND p1.participantId = :participant1
            AND p2.participantId = :participant2
            """)
    boolean existsDirectConversation(@Param("participant1") UUID participant1,
                                     @Param("participant2") UUID participant2);

}
