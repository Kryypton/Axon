package com.radonn.axon.exceptions;

public class CharacterNotFoundException extends Exception {
    public CharacterNotFoundException(String errorType) {
        super(errorType);
    }

    public CharacterNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
