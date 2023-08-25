package com.radonn.axon.exceptions;

public class CharacterMediaNotFoundException extends Exception {
    public CharacterMediaNotFoundException(String errorType) {
        super(errorType);
    }

    public CharacterMediaNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
