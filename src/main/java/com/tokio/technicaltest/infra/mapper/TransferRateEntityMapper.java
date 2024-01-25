package com.tokio.technicaltest.infra.mapper;

import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.infra.db.entity.TransferRateEntity;

import java.util.Objects;

public class TransferRateEntityMapper {

    private TransferRateEntityMapper() {
        throw new IllegalStateException("Mapper Class");
    }

    public static TransferRate fromTransferRateEntityToTransferRate(TransferRateEntity transferRateEntity) {

        if (Objects.isNull(transferRateEntity)) {
            return TransferRate.builder().build();
        }

        return TransferRate.builder()
                .id(transferRateEntity.getId())
                .fromDay(transferRateEntity.getFromDay())
                .untilDay(transferRateEntity.getUntilDay())
                .transferRate(transferRateEntity.getTransferRate())
                .status(transferRateEntity.isStatus())
                .build();

    }

}
