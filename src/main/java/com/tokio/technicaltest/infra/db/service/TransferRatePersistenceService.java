package com.tokio.technicaltest.infra.db.service;

import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.domain.port.outbound.TransferRatePersistencePort;
import com.tokio.technicaltest.infra.db.entity.TransferRateEntity;
import com.tokio.technicaltest.infra.db.repository.TransferRateRepository;
import com.tokio.technicaltest.infra.mapper.TransferRateEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tokio.technicaltest.domain.utils.LogUtils.*;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
@Component
public class TransferRatePersistenceService implements TransferRatePersistencePort {

    private final TransferRateRepository transferRateRepository;

    public TransferRatePersistenceService(TransferRateRepository transferRateRepository) {
        this.transferRateRepository = transferRateRepository;
    }

    @Override
    public List<TransferRate> retrieveAllTransferRates() throws IllegalStateException {

        final var start = now();
        log.info("status={}", STARTED);

        List<TransferRateEntity> transferRateEntities;

        try {
            transferRateEntities = transferRateRepository.findAll();
        } catch (Exception exception) {
            log.error("status={}, timeMillis={} ", FAILED, start.until(now(), MILLIS));
            throw new IllegalStateException(exception.getMessage());
        }

        log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
        return transferRateEntities.stream().map(TransferRateEntityMapper::fromTransferRateEntityToTransferRate).toList();

    }

}
