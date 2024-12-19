package com.cristian.msconversationsservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Size(min = 2, message = "A conversation must have at least two participants")
    @JsonManagedReference
    private List<Participant> participants = new ArrayList<>();

    private boolean isGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_metadata_id", referencedColumnName = "id")
    private GroupMetadata groupMetadata;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastMessageAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.lastMessageAt = null;
        validateParticipants();
    }

    @PreUpdate
    public void preUpdate() {
        validateParticipants();
    }

    private void validateParticipants() {
        if (isGroup) {
            if (participants.size() < 2) {
                throw new IllegalStateException("A group should have at least two participants.");
            }
            if (groupMetadata == null) {
                throw new IllegalStateException("A group conversation must have group metadata.");
            }
        } else {
            if (participants.size() != 2) {
                throw new IllegalStateException("Individual conversations must have exactly two participants.");
            }
            if (groupMetadata != null) {
                throw new IllegalStateException("Individual conversations cannot have group metadata.");
            }
        }
    }
}
