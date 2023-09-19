package com.radonn.axon.exceptions;

public class UserLgeCharacterAlreadyExistException extends Exception {
    public UserLgeCharacterAlreadyExistException(String errorType) {
        super(errorType);
    }
}
