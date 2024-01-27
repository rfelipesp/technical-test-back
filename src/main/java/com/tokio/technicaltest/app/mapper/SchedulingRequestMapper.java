package com.tokio.technicaltest.app.mapper;

import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.domain.model.Scheduling;

import java.util.Objects;

public class SchedulingRequestMapper {

    private SchedulingRequestMapper() {
        throw new IllegalStateException("Mapper Class");
    }

    public static Scheduling fromSchedulingRequestToScheduling(final SchedulingRequestAndResponse scheduling) {

        if (Objects.isNull(scheduling)) {
            return Scheduling.builder().build();
        }

        if (Objects.isNull(scheduling.getUuid())) {
            return Scheduling.builder()
                    .originAccount(scheduling.getOriginAccount())
                    .destinationAccount(scheduling.getDestinationAccount())
                    .transferAmount(scheduling.getTransferAmount())
                    .transferDate(scheduling.getTransferDate())
                    .schedulingDate(scheduling.getSchedulingDate())
                    .build();
        } else {
            return Scheduling.builder()
                    .uuid(scheduling.getUuid())
                    .originAccount(scheduling.getOriginAccount())
                    .destinationAccount(scheduling.getDestinationAccount())
                    .transferAmount(scheduling.getTransferAmount())
                    .transferDate(scheduling.getTransferDate())
                    .schedulingDate(scheduling.getSchedulingDate())
                    .build();
        }

    }

    public static SchedulingRequestAndResponse fromSchedulingToScheduleResponse(Scheduling scheduling) {

        if (Objects.isNull(scheduling)) {
            return SchedulingRequestAndResponse.builder().build();
        }

        return SchedulingRequestAndResponse.builder()
                .uuid(scheduling.getUuid())
                .originAccount(scheduling.getOriginAccount())
                .destinationAccount(scheduling.getDestinationAccount())
                .transferAmount(scheduling.getTransferAmount())
                .transferRate(scheduling.getTransferRate().getTransferRate())
                .transferDate(scheduling.getTransferDate())
                .schedulingDate(scheduling.getSchedulingDate())
                .status(scheduling.getTransferStatus().toString())
                .build();
    }

}
