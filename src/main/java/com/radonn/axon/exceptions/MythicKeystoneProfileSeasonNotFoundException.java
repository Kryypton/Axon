package com.radonn.axon.exceptions;

public class MythicKeystoneProfileSeasonNotFoundException extends Exception {
    public MythicKeystoneProfileSeasonNotFoundException(String errorType) {
        super(errorType);
    }

    public MythicKeystoneProfileSeasonNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
