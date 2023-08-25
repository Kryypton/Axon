package com.radonn.axon.models.wow.models.titles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.Character;

public class Titles {
    private Character character;
    @JsonProperty("active_title")
    private ActiveTitle activeTitle;
    private List<Title> titles;

	public Character getCharacter() {
		return character;
	}

	public ActiveTitle getActiveTitle() {
		return activeTitle;
	}

	public List<Title> getTitles() {
		return titles;
	}
}
