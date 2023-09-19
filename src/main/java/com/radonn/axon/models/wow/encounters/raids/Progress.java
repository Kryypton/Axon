package com.radonn.axon.models.wow.encounters.raids;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Progress {

    @JsonProperty("completed_count")
    private int completedCount;
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("encounters")
    private List<Encounters> encounters;

    public int getCompletedCount() {
        return completedCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Encounters> getEncounters() {
        return encounters;
    }
}
