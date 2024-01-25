package com.tokio.technicaltest.infra.mapper;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.infra.db.entity.SchedulingEntity;

import java.util.Objects;

public class SchedulingEntityMapper {

    private SchedulingEntityMapper() {
        throw new IllegalStateException("Mapper Class");
    }

    public static SchedulingEntity fromSchedulingToScheduleEntity(Scheduling scheduling) {

        final var schedulingEntity = new SchedulingEntity();

        if (Objects.nonNull(scheduling)) {

            if (Objects.nonNull(scheduling.getUuid())) {
                schedulingEntity.setUuid(scheduling.getUuid());
            }

            schedulingEntity.setOriginAccount(scheduling.getOriginAccount());
            schedulingEntity.setDestinationAccount(scheduling.getOriginAccount());
            schedulingEntity.setTransferAmount(scheduling.getTransferAmount());
//            schedulingEntity.setTransferRate(scheduling.getTransferRate());
            schedulingEntity.setTransferDate(scheduling.getTransferDate());
            schedulingEntity.setSchedulingDate(scheduling.getSchedulingDate());
            schedulingEntity.setTransferStatus(scheduling.getTransferStatus());

        }

        return schedulingEntity;
    }

    public static Scheduling fromSchedulingEntityToSchedule(SchedulingEntity schedulingEntity) {

        if (Objects.isNull(schedulingEntity)) {
            return Scheduling.builder().build();
        }

        var transferRate = TransferRate.builder()
                .id(schedulingEntity.getTransferRateEntity().getId())
                .fromDay(schedulingEntity.getTransferRateEntity().getFromDay())
                .untilDay(schedulingEntity.getTransferRateEntity().getUntilDay())
                .transferRate(schedulingEntity.getTransferRateEntity().getTransferRate())
                .status(schedulingEntity.getTransferRateEntity().isStatus())
                .build();

        return Scheduling.builder()
                .uuid(schedulingEntity.getUuid())
                .originAccount(schedulingEntity.getOriginAccount())
                .destinationAccount(schedulingEntity.getDestinationAccount())
                .transferAmount(schedulingEntity.getTransferAmount())
                .transferRate(transferRate)
                .transferDate(schedulingEntity.getTransferDate())
                .schedulingDate(schedulingEntity.getSchedulingDate())
                .transferStatus(schedulingEntity.getTransferStatus())
                .build();

    }

}
