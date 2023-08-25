package com.radonn.axon.services.battleNetOAuth;

public class BattleNetOAuthClientValidException extends Exception {
    public BattleNetOAuthClientValidException(String errorType, String errorDescription) {
        super(errorType + "\n" + errorDescription);
    }

    public BattleNetOAuthClientValidException(String errorType, String errorDescription, Throwable cause) {
        super(errorType + "\n" + errorDescription, cause);
    }
}
