package com.radonn.axon.models.wow.models.mythicKeystoneProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.Character;
import com.radonn.axon.models.wow.models.base.Playable;

public class CharacterMythic {
    private Character character;
    private Playable specialization;
    private Playable race;
    @JsonProperty("equipped_item_level")
    private int equippedItemLevel;

	public Character getCharacter() {
		return character;
	}
	public Playable getSpecialization() {
		return specialization;
	}
	public Playable getRace() {
		return race;
	}
	public int getEquippedItemLevel() {
		return equippedItemLevel;
	}
}
