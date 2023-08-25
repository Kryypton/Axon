package com.radonn.axon.exceptions;

public class PlayableSpecializationNotFoundException extends Exception {
    public PlayableSpecializationNotFoundException(String errorType) {
        super(errorType);
    }

    public PlayableSpecializationNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
