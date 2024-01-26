package com.tokio.technicaltest;

import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.model.TransferRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface MainHelper {

    static Scheduling buildScheduling(){

        var transferRate = TransferRate.builder()
                .id(1L)
                .fromDay(0)
                .untilDay(0)
                .transferRate(new BigDecimal("2.5"))
                .status(true)
                .build();

        return Scheduling.builder()
                .uuid(UUID.fromString("f93bd959-2e22-40f3-8483-86f3c0dd6d4f"))
                .originAccount(1000L)
                .destinationAccount(1001L)
                .transferAmount(new BigDecimal("100.00"))
                .transferRate(transferRate)
                .transferDate(LocalDate.of(2024, 1, 25))
                .schedulingDate(LocalDate.of(2024, 1, 25))
                .build();
    }

    static SchedulingRequestAndResponse buildSchedulingRequest(){

        return SchedulingRequestAndResponse.builder()
                .uuid(UUID.fromString("f93bd959-2e22-40f3-8483-86f3c0dd6d4f"))
                .originAccount(1000L)
                .destinationAccount(1001L)
                .transferAmount(new BigDecimal("100.00"))
                .transferDate(LocalDate.now())
                .build();
    }


}
