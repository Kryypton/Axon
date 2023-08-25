package com.radonn.axon.models.wow.models.guild;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.models.wow.models.base.Descriptor;
import com.radonn.axon.models.wow.models.base.Link;
import com.radonn.axon.models.wow.models.base.Links;
import com.radonn.axon.models.wow.models.base.Realm;

public class Guild {
    
    @JsonProperty("_links")
    private Links links;
    private int id;
    private String name;
    private Descriptor faction;
    @JsonProperty("achievement_points")
    private int achievementPoints;
    @JsonProperty("member_count")
    private int memberCount;
    @JsonProperty("realm")
    private Realm realm;
    private Crest crest;
    private Link roster;
    private Link achievements;
    @JsonProperty("created_timestamp")
    private long createdTimestamp;
    private Link activity;

	public Links getLinks() {
		return links;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Descriptor getFaction() {
		return faction;
	}

	public int getAchievementPoints() {
		return achievementPoints;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public Realm getRealm() {
		return realm;
	}

	public Crest getCrest() {
		return crest;
	}

	public Link getRoster() {
		return roster;
	}

	public Link getAchievements() {
		return achievements;
	}

	public long getCreatedTimestamp() {
		return createdTimestamp;
	}

	public Link getActivity() {
		return activity;
	}

    //// 

    public GuildRoster getGuildRoster() {
        return new BlizzardApiController().getGuildRoster(this.realm.getName().toLowerCase().replace(" ", "-"), this.name.toLowerCase().replace(" ", "-"));
    }
}
