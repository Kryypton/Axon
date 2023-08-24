package com.radonn.axon.wowApi.models.mythicKeystoneProfile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.controllers.BlizzardApiController;
import com.radonn.axon.wowApi.models.Character;
import com.radonn.axon.wowApi.models.base.Item;
import com.radonn.axon.wowApi.models.base.Links;

public class MythicKeystoneProfile {

    @JsonProperty("_links")
    private Links links;
    @JsonProperty("current_period")
    private CurrentPeriod currentPeriod;
    private List<Item> seasons;
    private Character character;
    @JsonProperty("current_mythic_rating")
    private Rating currentMythicRating;

	public Links getLinks() {
		return links;
	}

	public CurrentPeriod getCurrentPeriod() {
		return currentPeriod;
	}

	public List<Item> getSeasons() {
		return seasons;
	}

	public Character getCharacter() {
		return character;
	}

	public Rating getCurrentMythicRating() {
		return currentMythicRating;
	}

    //// 

    public MythicKeystoneProfileSeason getLastSeason() {
        if (this.getSeasons().size() == 0) {
            return null;
        }
        int seasonIdIndex = this.getSeasons().get(0).getId();
        for (Item season : this.getSeasons()) {
            if (season.getId() > seasonIdIndex) {
                seasonIdIndex = season.getId();
            }
        }
        return new BlizzardApiController().getMythicKeystoneProfileSeason(this.character.getRealm().getSlug().toLowerCase(), this.character.getName().toLowerCase(), seasonIdIndex);
    }
}
