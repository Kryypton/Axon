package com.radonn.axon.wowApi.services.battleNetOAuth;

public class BattleNetOAuthClientNullException extends RuntimeException {
    public BattleNetOAuthClientNullException(String errorType) {
        super(errorType);
    }

    public BattleNetOAuthClientNullException(String errorType, Throwable cause) {
        super(errorType, cause);
    }
}
