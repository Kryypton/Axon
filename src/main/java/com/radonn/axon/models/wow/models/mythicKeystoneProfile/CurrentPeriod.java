package com.radonn.axon.models.wow.models.mythicKeystoneProfile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.base.Item;

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
