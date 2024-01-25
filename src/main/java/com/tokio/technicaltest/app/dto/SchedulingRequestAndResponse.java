package com.tokio.technicaltest.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SchedulingRequestAndResponse {

    private UUID uuid;

    @NotNull
    private Long originAccount;

    @NotNull
    private Long destinationAccount;

    @NotNull
    private BigDecimal transferAmount;

    @NotNull
    private BigDecimal transferRate;

    @NotNull
    private LocalDate transferDate;

    @NotNull
    private LocalDate schedulingDate;

}
