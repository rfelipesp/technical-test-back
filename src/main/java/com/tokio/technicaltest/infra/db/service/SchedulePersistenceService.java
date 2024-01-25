package com.tokio.technicaltest.infra.db.service;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.port.outbound.SchedulePersistencePort;
import com.tokio.technicaltest.infra.db.repository.SchedulingRepository;
import com.tokio.technicaltest.infra.mapper.SchedulingEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulePersistenceService implements SchedulePersistencePort {

    private final SchedulingRepository schedulingRepository;

    public SchedulePersistenceService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public List<Scheduling> retrieveAllSchedules() {


        try {
            return schedulingRepository.findAll().stream()
                    .map(SchedulingEntityMapper::fromSchedulingEntityToSchedule).toList();
        } catch (Exception exception) {

        }

        return null;
    }

}
