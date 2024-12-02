package com.cristian.msconversationsservice.model;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "conversations")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "conversation_participants", joinColumns = @JoinColumn(name = "conversation_id"))
    @Column(name = "participant_id")
    private List<UUID> participants;

    private boolean isGroup;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastMessageAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        lastMessageAt = null;
    }
}
