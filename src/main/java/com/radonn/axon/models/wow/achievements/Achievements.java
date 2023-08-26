package com.radonn.axon.models.wow.achievements;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.base.Link;
import com.radonn.axon.models.wow.base.Links;

public class Achievements {
    
    @JsonProperty("_links")
    private Links links;
    @JsonProperty("total_quantity")
    private int totalQuantity;
    @JsonProperty("total_points")
    private int totalPoints;
    @JsonProperty("achievements")
    private List<Achievement> achievements;
    @JsonProperty("category_progress")
    private List<CategoryProgress> categoryProgress;
    @JsonProperty("recent_events")
    private List<RecentEvent> recentEvents;
    private Character character;
    private Link statistics;

	public Links getLinks() {
		return links;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public List<CategoryProgress> getCategoryProgress() {
		return categoryProgress;
	}

	public List<RecentEvent> getRecentEvents() {
		return recentEvents;
	}

	public Character getCharacter() {
		return character;
	}

	public Link getStatistics() {
		return statistics;
	}
}
