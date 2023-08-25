package com.radonn.axon.models.wow.models.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.base.Criteria;
import com.radonn.axon.models.wow.models.base.Playable;

public class Achievement {
    private int id;
    private Playable achievement;
    private Criteria criteria;
    @JsonProperty("completed_timestamp")
    private long completed_timestamp;

	public int getId() {
		return id;
	}

	public Playable getAchievement() {
		return achievement;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public long getCompleted_timestamp() {
		return completed_timestamp;
	}
}
