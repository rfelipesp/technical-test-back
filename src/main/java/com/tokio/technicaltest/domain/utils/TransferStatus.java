package com.tokio.technicaltest.domain.utils;

public enum TransferStatus {

    SCHEDULED("scheduled"),
    WAITING("waiting"),
    PROCESSED("processed"),
    CANCELED("canceled"),
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
