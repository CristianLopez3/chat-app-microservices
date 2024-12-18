package com.cristian.msconversationsservice.repository;

import com.cristian.msconversationsservice.model.GroupMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMetadataRepository extends JpaRepository<GroupMetadata, Long> {
}
