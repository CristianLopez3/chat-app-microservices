package com.cristian.msconversationsservice.dto;

public interface ConversationProjection {
    Long getId();
    boolean getIsGroup();
    GroupMetadataProjection getGroupMetadata();

    interface GroupMetadataProjection {
        Long getId();
        String getName();
        String getDescription();
    }

}
