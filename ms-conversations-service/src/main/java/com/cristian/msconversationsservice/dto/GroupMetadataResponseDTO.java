package com.cristian.msconversationsservice.dto;

import com.cristian.msconversationsservice.model.GroupMetadata;
import lombok.Builder;

@Builder
public record GroupMetadataResponseDTO(
        String name,
        String description
) {

    public GroupMetadataResponseDTO(GroupMetadata groupMetadata) {
        this(groupMetadata.getName(), groupMetadata.getDescription());
    }

}
