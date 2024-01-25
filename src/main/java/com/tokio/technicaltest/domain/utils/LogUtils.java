package com.tokio.technicaltest.domain.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogUtils {

    STARTED("started"),
    FINISHED("finished"),
    EXECUTED("executed"),
    FAILED("failed"),
    IGNORED("ignored");

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }


}
