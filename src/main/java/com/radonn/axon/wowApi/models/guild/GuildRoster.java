package com.radonn.axon.wowApi.models.guild;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.base.Links;

public class GuildRoster {

    @JsonProperty("_links")
    private Links links;
    private Guild guild;
    private List<CharacterGuild> members;

    public Links getLinks() {
        return links;
    }

    public Guild getGuild() {
        return guild;
    }

    public List<CharacterGuild> getMembers() {
        return members;
    }

    //// 

    public Boolean isInGuild(String name) {
        return members.stream().anyMatch(member -> member.getCharacter().getName().toLowerCase().equals(name.toLowerCase()));
    }

    public int getMemberRank(String name) {
        return members.stream().filter(member -> member.getCharacter().getName().equals(name)).findFirst().get().getRank();
    }
}
