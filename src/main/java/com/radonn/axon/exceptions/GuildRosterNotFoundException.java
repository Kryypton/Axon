package com.radonn.axon.exceptions;

public class GuildRosterNotFoundException extends Exception {
    public GuildRosterNotFoundException(String errorType) {
        super(errorType);
    }

    public GuildRosterNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
