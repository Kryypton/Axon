package com.radonn.axon.models.wow.encounters.raids;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.base.Playable;

public class Encounters {

    private Playable encounter;
    @JsonProperty("completed_count")
    private int completedCount;
    @JsonProperty("last_kill_timestamp")
    private long lastKillTimestamp;

    public Playable getEncounter() {
        return encounter;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public long getLastKillTimestamp() {
        return lastKillTimestamp;
    }

}
