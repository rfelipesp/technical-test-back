package com.tokio.technicaltest.infra.db.repository;

import com.tokio.technicaltest.infra.db.entity.SchedulingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<SchedulingEntity, UUID> {
    
}
