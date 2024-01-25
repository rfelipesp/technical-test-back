package com.tokio.technicaltest.infra.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "transfer_rate")
public class TransferRateEntity {

    @Id
    private Long id;

    @NotNull
    @Column(name = "transfer_rate")
    private BigDecimal transferRate;

    @NotNull
    @Column(name = "from_day")
    private Integer fromDay;

    @NotNull
    @Column(name = "until_day")
    private Integer untilDay;

    @Column(name = "status")
    private boolean status;

}
