package com.tokio.technicaltest.domain.service;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import com.tokio.technicaltest.domain.port.outbound.SchedulePersistencePort;
import com.tokio.technicaltest.domain.port.outbound.TransferRatePersistencePort;
import com.tokio.technicaltest.domain.utils.TransferStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class SchedulingService implements SchedulingInboundPort {

    private final SchedulePersistencePort schedulePersistencePort;
    private final TransferRateService transferRateService;

    public SchedulingService(SchedulePersistencePort schedulePersistencePort, TransferRatePersistencePort transferRatePersistencePort, TransferRateService transferRateService) {
        this.schedulePersistencePort = schedulePersistencePort;
        this.transferRateService = transferRateService;
    }

    @Override
    public List<Scheduling> findSchedules() {
        return schedulePersistencePort.retrieveAllSchedules();
    }

    @Transactional
    @Override
    public Scheduling saveScheduling(Scheduling scheduling) {

        var period = scheduling.getSchedulingDate().getDayOfYear() - LocalDate.now().getDayOfYear();
        var transferRate = transferRateService.retrieveTransferRateByPeriod(period);

        if (Objects.isNull(transferRate.getId())) {
            return Scheduling.builder().build();
        }

        scheduling.setTransferRate(transferRate);
        scheduling.setTransferStatus(TransferStatus.SCHEDULED);
        return schedulePersistencePort.saveScheduling(scheduling);

    }
}
