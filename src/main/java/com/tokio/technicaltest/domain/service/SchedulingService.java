package com.tokio.technicaltest.domain.service;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import com.tokio.technicaltest.domain.port.outbound.SchedulePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService implements SchedulingInboundPort {

    private final SchedulePersistencePort schedulePersistencePort;

    public SchedulingService(SchedulePersistencePort schedulePersistencePort) {
        this.schedulePersistencePort = schedulePersistencePort;
    }

    @Override
    public List<Scheduling> findSchedules() {
        return schedulePersistencePort.retrieveAllSchedules();
    }
}
