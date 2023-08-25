package com.radonn.axon.models.wow.models.mythicKeystoneProfile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.base.Playable;

public class BestRun implements Comparable<BestRun> {
    @JsonProperty("completed_timestamp")
    private long completedTimestamp;
    private int duration;
    @JsonProperty("keystone_level")
    private int keystoneLevel;
    @JsonProperty("keystone_affixes")
    private List<Playable> keystoneAffixes;
    private List<CharacterMythic> members;
    private Playable dungeon;
    @JsonProperty("is_completed_within_time")
    private boolean isCompletedWithinTime;
    @JsonProperty("mythic_rating")
    private Rating mythicRating;
    @JsonProperty("map_rating")
    private Rating mapRating;

	public long getCompletedTimestamp() {
		return completedTimestamp;
	}

	public int getDuration() {
		return duration;
	}

	public int getKeystoneLevel() {
		return keystoneLevel;
	}

	public List<Playable> getKeystoneAffixes() {
		return keystoneAffixes;
	}

	public List<CharacterMythic> getMembers() {
		return members;
	}

	public Playable getDungeon() {
		return dungeon;
	}

	public boolean isCompletedWithinTime() {
		return isCompletedWithinTime;
	}

	public Rating getMythicRating() {
		return mythicRating;
	}

	public Rating getMapRating() {
		return mapRating;
	}

	@Override
	public int compareTo(BestRun arg0) {
		return (int) (this.getMythicRating().getRating() - arg0.getMythicRating().getRating());
	}
}
