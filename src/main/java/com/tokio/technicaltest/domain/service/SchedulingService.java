package com.tokio.technicaltest.domain.service;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import com.tokio.technicaltest.domain.port.outbound.SchedulingPersistencePort;
import com.tokio.technicaltest.domain.port.outbound.TransferRatePersistencePort;
import com.tokio.technicaltest.domain.utils.TransferStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SchedulingService implements SchedulingInboundPort {

    private final SchedulingPersistencePort schedulingPersistencePort;
    private final TransferRateService transferRateService;

    public SchedulingService(SchedulingPersistencePort schedulingPersistencePort, TransferRatePersistencePort transferRatePersistencePort, TransferRateService transferRateService) {
        this.schedulingPersistencePort = schedulingPersistencePort;
        this.transferRateService = transferRateService;
    }

    @Override
    public List<Scheduling> findSchedules() {
        return schedulingPersistencePort.retrieveAllSchedules();
    }

    @Transactional
    @Override
    public Scheduling saveScheduling(Scheduling scheduling) {

        var today = LocalDate.now();
        var period = scheduling.getTransferDate().getDayOfYear() - today.getDayOfYear();
        var transferRate = transferRateService.retrieveTransferRateByPeriod(period);

        if (Objects.isNull(transferRate.getId())) {
            return Scheduling.builder().build();
        }

        scheduling.setTransferRate(transferRate);
        scheduling.setSchedulingDate(today);
        scheduling.setTransferStatus(TransferStatus.SCHEDULED);
        return schedulingPersistencePort.saveScheduling(scheduling);

    }

    @Transactional
    @Override
    public void deleteScheduling(UUID uuid) {

        var scheduling = schedulingPersistencePort.retrieveSchedulingById(uuid);
        scheduling.setTransferStatus(TransferStatus.CANCELED);
        schedulingPersistencePort.deleteScheduling(scheduling);
    }
}
