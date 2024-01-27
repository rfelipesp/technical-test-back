package com.tokio.technicaltest.domain.port.outbound;

import com.tokio.technicaltest.domain.model.Scheduling;

import java.util.List;
import java.util.UUID;

public interface SchedulingPersistencePort {

    List<Scheduling> retrieveAllSchedules();

    Scheduling retrieveSchedulingById(UUID uuid);

    Scheduling saveScheduling(Scheduling scheduling);

    void deleteScheduling(Scheduling scheduling);


}
