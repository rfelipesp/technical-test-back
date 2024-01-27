package com.tokio.technicaltest.domain.port.inbound;

import com.tokio.technicaltest.domain.model.Scheduling;

import java.util.List;
import java.util.UUID;

public interface SchedulingInboundPort {

    List<Scheduling> findSchedules();

    Scheduling saveScheduling(Scheduling scheduling);

    void deleteScheduling(UUID uuid);

    Scheduling getOneScheduling(UUID uuid);

}
