package com.radonn.axon.wowApi.models.specializations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.controllers.BlizzardApiController;
import com.radonn.axon.wowApi.models.base.Descriptor;
import com.radonn.axon.wowApi.models.base.Links;
import com.radonn.axon.wowApi.models.base.Playable;

public class Specializations {
    
    @JsonProperty("_links")
    private Links links;
    private List<Specialization> specialization;
    @JsonProperty("active_specialization")
    private Playable activeSpecialization;
    private Character character;

	public Links getLinks() {
		return links;
	}

	public List<Specialization> getSpecialization() {
		return specialization;
	}

	public Playable getActiveSpecialization() {
		return activeSpecialization;
	}

	public Character getCharacter() {
		return character;
	}

    public Descriptor getRole() {
        return new BlizzardApiController().getPlayableSpecialisation(this.getActiveSpecialization().getId()).getRole();
    }
}
