package com.tokio.technicaltest.domain.port.inbound;

import com.tokio.technicaltest.domain.model.Scheduling;

import java.util.Arrays;
import java.util.List;

public interface SchedulingInboundPort {

    List<Scheduling> findSchedules();

    Scheduling saveScheduling(Scheduling scheduling);
}
