package com.tokio.technicaltest.infra.db.repository;

import com.tokio.technicaltest.infra.db.entity.TransferRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRateRepository extends JpaRepository<TransferRateEntity, Long> {
}
