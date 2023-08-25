package com.radonn.axon.exceptions;

public class MythicKeystoneProfileNotFoundException extends Exception {
    public MythicKeystoneProfileNotFoundException(String errorType) {
        super(errorType);
    }

    public MythicKeystoneProfileNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}