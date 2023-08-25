package com.radonn.axon.exceptions;

public class AchievementsNotFoundException extends Exception {
    public AchievementsNotFoundException(String errorType) {
        super(errorType);
    }

    public AchievementsNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
