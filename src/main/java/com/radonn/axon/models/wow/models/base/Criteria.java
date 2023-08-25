package com.radonn.axon.models.wow.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Criteria {
    private int id;
    @JsonProperty("is_completed")
    private Boolean isCompleted;

	public int getId() {
		return id;
	}
    
	public Boolean getIsCompleted() {
		return isCompleted;
	}
}
