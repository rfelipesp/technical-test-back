package com.tokio.technicaltest.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private BigDecimal transferRate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transferDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate schedulingDate;

}
