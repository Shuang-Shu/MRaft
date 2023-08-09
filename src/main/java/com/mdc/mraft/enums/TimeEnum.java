package com.mdc.mraft.enums;

import java.util.concurrent.TimeUnit;

public enum TimeEnum {
    TIMEOUT(3), SLEEP_TIME(100);
    private final int value;

    TimeEnum(int time) {
        value = time;
    }

    int getValue() {
        return value;
    }
}
