package com.tokio.technicaltest.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRate {

    private Long id;
    private BigDecimal transferRate;
    private Integer fromDay;
    private Integer untilDay;
    private Boolean status;

}
