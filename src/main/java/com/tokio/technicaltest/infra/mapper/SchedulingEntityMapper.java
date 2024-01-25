package com.tokio.technicaltest.infra.mapper;

import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.infra.db.entity.SchedulingEntity;
import com.tokio.technicaltest.infra.db.entity.TransferRateEntity;

import java.util.Objects;

public class SchedulingEntityMapper {

    private SchedulingEntityMapper() {
        throw new IllegalStateException("Mapper Class");
    }

    public static SchedulingEntity fromSchedulingToScheduleEntity(Scheduling scheduling) {

        if (Objects.isNull(scheduling) || Objects.isNull(scheduling.getTransferRate())) {
            return new SchedulingEntity();
        }

        var transferRate = new TransferRateEntity();
        transferRate.setId(scheduling.getTransferRate().getId());
        transferRate.setFromDay(scheduling.getTransferRate().getFromDay());
        transferRate.setUntilDay(scheduling.getTransferRate().getUntilDay());
        transferRate.setTransferRate(scheduling.getTransferRate().getTransferRate());
        transferRate.setStatus(scheduling.getTransferRate().getStatus());

        final var schedulingEntity = new SchedulingEntity();

        if (Objects.nonNull(scheduling.getUuid())) {
            schedulingEntity.setUuid(scheduling.getUuid());
        }

        schedulingEntity.setOriginAccount(scheduling.getOriginAccount());
        schedulingEntity.setDestinationAccount(scheduling.getDestinationAccount());
        schedulingEntity.setTransferAmount(scheduling.getTransferAmount());
        schedulingEntity.setTransferRateEntity(transferRate);
        schedulingEntity.setTransferDate(scheduling.getTransferDate());
        schedulingEntity.setSchedulingDate(scheduling.getSchedulingDate());
        schedulingEntity.setTransferStatus(scheduling.getTransferStatus());


        return schedulingEntity;
    }

    public static Scheduling fromSchedulingEntityToSchedule(SchedulingEntity schedulingEntity) {

        if (Objects.isNull(schedulingEntity) || Objects.isNull(schedulingEntity.getTransferRateEntity())) {
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
