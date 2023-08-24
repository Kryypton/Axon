package com.radonn.axon.wowApi.models.mythicKeystoneProfile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.base.Item;

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
