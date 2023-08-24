package com.radonn.axon.wowApi.models.mythicKeystoneProfile;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.Character;
import com.radonn.axon.wowApi.models.base.Item;
import com.radonn.axon.wowApi.models.base.Links;

public class MythicKeystoneProfileSeason {
    @JsonProperty("_links")
    private Links links;
    private Item season;
    @JsonProperty("best_runs")
    private List<BestRun> bestRun;
    private Character character;
    @JsonProperty("mythic_rating")
    private Rating mythicRating;

	public Links getLinks() {
		return links;
	}

	public Item getSeason() {
		return season;
	}

	public List<BestRun> getBestRun() {
		return bestRun;
	}

	public Character getCharacter() {
		return character;
	}

	public Rating getMythicRating() {
		return mythicRating;
	}

    public BestRun getTheBestRun() {
        if (this.bestRun == null || this.bestRun.isEmpty()) {
        return null;
    }
        return Collections.max(this.bestRun);
    }

    public BestRun getTheBaddestRun() {
        if (this.bestRun == null || this.bestRun.isEmpty()) {
        return null;
    }
        return Collections.min(this.bestRun);
    }

    public BestRun getBestRunForAffix(String affixName) {
        if (bestRun == null || bestRun.isEmpty()) {
            return null;
        }
        
        return bestRun.stream()
            .filter(run -> run.getKeystoneAffixes().stream()
                    .anyMatch(affix -> affix.getName().equalsIgnoreCase(affixName)))
            .max(BestRun::compareTo)
            .orElse(null);
    }

    public BestRun getBestRunForTyranical() {
        return getBestRunForAffix("tyrannique");
    }

    public BestRun getBestRunForFortified() {
        return getBestRunForAffix("fortifié");
    }
}
