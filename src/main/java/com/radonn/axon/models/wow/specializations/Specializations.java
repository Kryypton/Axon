package com.radonn.axon.models.wow.specializations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.exceptions.PlayableSpecializationNotFoundException;
import com.radonn.axon.models.wow.base.Descriptor;
import com.radonn.axon.models.wow.base.Links;
import com.radonn.axon.models.wow.base.Playable;

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
        try {
			return new BlizzardApiController().getPlayableSpecialisation(this.getActiveSpecialization().getId()).getRole();
		} catch (PlayableSpecializationNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }
}
