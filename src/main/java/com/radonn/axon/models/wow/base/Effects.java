package com.radonn.axon.models.wow.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Effects {
    @JsonProperty("display_string")
    private String displayString;
    @JsonProperty("required_count")
    private int requiredCount;
    @JsonProperty("is_active")
    private boolean isActive;

	public String getDisplayString() {
		return displayString;
	}

	public int getRequiredCount() {
		return requiredCount;
	}

	public Boolean getIsActive() {
		return isActive;
	}
}
