package com.radonn.axon.wowApi.models.characterMedia;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.Character;
import com.radonn.axon.wowApi.models.base.Links;

public class CharacterMedia {
    
    @JsonProperty("_links")
    private Links links;
    private Character character;
    private List<Assets> assets;

	public Links getLinks() {
		return links;
	}

	public Character getCharacter() {
		return character;
	}

	public List<Assets> getAssets() {
		return assets;
	}

    public String getAvatarUrl() {
        return assets.stream().filter(asset -> asset.getKey().equals("avatar")).findFirst().get().getValue();
    }

    public String getinsetUrl() {
        return assets.stream().filter(asset -> asset.getKey().equals("inset")).findFirst().get().getValue();
    }

    public String getMainRawUrl() {
        return assets.stream().filter(asset -> asset.getKey().equals("main-raw")).findFirst().get().getValue();
    }
}
