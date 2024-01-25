package com.tokio.technicaltest.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransferRate {

    private Long id;
    private BigDecimal transferRate;
    private Integer fromDay;
    private Integer untilDay;
    private Boolean status;

}
