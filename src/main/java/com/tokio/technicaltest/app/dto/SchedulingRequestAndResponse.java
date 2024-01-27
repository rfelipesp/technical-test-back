package com.tokio.technicaltest.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingRequestAndResponse {

    private UUID uuid;

    @NotNull(message = "destinationAccount | Conta de origem deve ser informada")
    private Long originAccount;

    @NotNull(message = "destinationAccount | Conta de destino deve ser informada")
    private Long destinationAccount;

    @NotNull(message = "transferAmount | O valor a ser transferido deve ser informado")
    private BigDecimal transferAmount;

    private BigDecimal transferRate;

    @NotNull(message = "transferDate | A data de transferência deve ser informada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transferDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate schedulingDate;

    private String status;

}
