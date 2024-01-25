package com.tokio.technicaltest.infra.db.entity;

import com.tokio.technicaltest.domain.utils.TransferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "scheduling")
@Getter
@Setter
public class SchedulingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4232064991552198460L;

    @Id
    private UUID uuid;

    @NotNull
    @Column(name = "origin_account")
    private Long originAccount;

    @NotNull
    @Column(name = "destination_account")
    private Long destinationAccount;

    @NotNull
    @Column(name = "transfer_amount")
    private BigDecimal transferAmount;

    @NotNull
    @Column(name = "transfer_date")
    private LocalDate transferDate;


    @NotNull
    @Column(name = "scheduling_date")
    private LocalDate schedulingDate;

    @NotNull
    @Column(name = "transfer_status")
    private TransferStatus transferStatus;

    @ManyToOne
    @JoinColumn(name="transfer_rate_id", nullable=false)
    private TransferRateEntity transferRateEntity;



}
