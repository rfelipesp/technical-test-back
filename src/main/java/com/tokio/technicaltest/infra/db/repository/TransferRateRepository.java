package com.tokio.technicaltest.infra.db.repository;

import com.tokio.technicaltest.infra.db.entity.TransferRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRateRepository extends JpaRepository<TransferRateEntity, Long> {

    @Query("FROM TransferRateEntity tr WHERE tr.fromDay <= :period AND tr.untilDay >= :period AND tr.status = true")
    Optional<TransferRateEntity> findTransferRateByPeriod(Integer period);

}
