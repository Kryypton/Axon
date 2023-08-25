package com.radonn.axon.exceptions;

public class FetchDataException extends Exception{
    public FetchDataException(String errorType) {
        super(errorType);
    }

    public FetchDataException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
