package com.tokio.technicaltest.domain.utils;

public enum TransferStatus {

    SCHEDULED("AGENDADA"),
    WAITING("AGUARDANDO"),
    PROCESSED("PROCESSADA"),
    CANCELED("CANCELADA"),
    ;

    private final String status;

    TransferStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
