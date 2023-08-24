package com.radonn.axon.wowApi.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovenantProgress {
    @JsonProperty("chosen_covenant")
    private Playable chosenCovenant;
    @JsonProperty("renown_level")
    private int renownLevel;

	public Playable getChosenCovenant() {
		return chosenCovenant;
	}

	public int getRenownLevel() {
		return renownLevel;
	}
}
