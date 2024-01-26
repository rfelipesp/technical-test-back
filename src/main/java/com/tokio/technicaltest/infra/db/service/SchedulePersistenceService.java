package com.tokio.technicaltest.infra.db.service;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.port.outbound.SchedulePersistencePort;
import com.tokio.technicaltest.infra.db.entity.SchedulingEntity;
import com.tokio.technicaltest.infra.db.repository.SchedulingRepository;
import com.tokio.technicaltest.infra.mapper.SchedulingEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.tokio.technicaltest.domain.utils.LogUtils.*;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
@Component
public class SchedulePersistenceService implements SchedulePersistencePort {

    private final SchedulingRepository schedulingRepository;

    public SchedulePersistenceService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public List<Scheduling> retrieveAllSchedules() throws IllegalStateException {

        final var start = now();
        log.info("status={}", STARTED);

        List<SchedulingEntity> schedules;

        try {
            schedules = schedulingRepository.findAll();
        } catch (Exception exception) {
            log.error("status={}, timeMillis={} ", FAILED, start.until(now(), MILLIS));
            throw new IllegalStateException(exception.getMessage());
        }

        log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
        return schedules.stream().map(SchedulingEntityMapper::fromSchedulingEntityToSchedule).toList();

    }

    @Override
    public Scheduling retrieveSchedulingById(UUID uuid) throws IllegalStateException {

        final var start = now();
        log.info("status={}", STARTED);

        try {

            var optionalSchedule = schedulingRepository.findById(uuid);

            if (optionalSchedule.isEmpty()) {
                throw new IllegalStateException("Scheduling not found");
            }

            log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
            return SchedulingEntityMapper.fromSchedulingEntityToSchedule(optionalSchedule.get());

        } catch (Exception exception) {
            log.error("status={}, timeMillis={} ", FAILED, start.until(now(), MILLIS));
            throw new IllegalStateException(exception.getMessage());
        }

    }

    @Override
    public Scheduling saveScheduling(Scheduling scheduling) throws IllegalStateException {

        final var start = now();
        log.info("status={}", STARTED);

        SchedulingEntity schedulingPersisted;

        try {

            schedulingPersisted = schedulingRepository.saveAndFlush(
                    SchedulingEntityMapper.fromSchedulingToScheduleEntity(scheduling));

        } catch (Exception exception) {
            log.error("status={}, timeMillis={} ", FAILED, start.until(now(), MILLIS));
            throw new IllegalStateException(exception.getMessage());
        }

        log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
        return SchedulingEntityMapper.fromSchedulingEntityToSchedule(schedulingPersisted);

    }

    @Override
    public void deleteScheduling(Scheduling scheduling) throws IllegalStateException {

        final var start = now();
        log.info("status={}", STARTED);

        try {

            schedulingRepository.saveAndFlush(SchedulingEntityMapper.fromSchedulingToScheduleEntity(scheduling));

        } catch (Exception exception) {
            log.error("status={}, timeMillis={} ", FAILED, start.until(now(), MILLIS));
            throw new IllegalStateException(exception.getMessage());
        }

    }

}
