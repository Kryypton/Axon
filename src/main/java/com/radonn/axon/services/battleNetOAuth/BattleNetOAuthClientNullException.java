package com.radonn.axon.services.battleNetOAuth;

public class BattleNetOAuthClientNullException extends RuntimeException {
    public BattleNetOAuthClientNullException(String errorType) {
        super(errorType);
    }

    public BattleNetOAuthClientNullException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
