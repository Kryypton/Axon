package com.radonn.axon.exceptions;

public class EquipmentNotFoundException extends Exception {
    public EquipmentNotFoundException(String errorType) {
        super(errorType);
    }

    public EquipmentNotFoundException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}

