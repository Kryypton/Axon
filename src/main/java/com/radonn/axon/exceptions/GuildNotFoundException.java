package com.radonn.axon.exceptions;

public class GuildNotFoundException extends Exception {
    public GuildNotFoundException(String errorType) {
        super(errorType);
    }

    public GuildNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
