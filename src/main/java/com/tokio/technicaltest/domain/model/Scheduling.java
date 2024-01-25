package com.tokio.technicaltest.domain.model;

import com.tokio.technicaltest.domain.utils.TransferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Scheduling {

    private UUID uuid;
    private Long originAccount;
    private Long destinationAccount;
    private BigDecimal transferAmount;
    private TransferRate transferRate;
    private LocalDate transferDate;
    private LocalDate schedulingDate;
    private TransferStatus transferStatus;

}
