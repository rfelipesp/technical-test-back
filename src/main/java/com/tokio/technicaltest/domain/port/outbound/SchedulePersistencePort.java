package com.tokio.technicaltest.domain.port.outbound;

import com.tokio.technicaltest.domain.model.Scheduling;

import java.util.List;

public interface SchedulePersistencePort {

    List<Scheduling> retrieveAllSchedules();

}
