package com.radonn.axon.models.wow.mythicKeystoneProfile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.base.Item;

public class CurrentPeriod {
    private Item period;
    @JsonProperty("best_runs")
    private List<BestRun> bestRuns;

	public Item getPeriod() {
		return period;
	}

	public List<BestRun> getBestRuns() {
		return bestRuns;
	} 
}
