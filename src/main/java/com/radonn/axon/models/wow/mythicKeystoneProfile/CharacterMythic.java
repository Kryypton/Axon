package com.radonn.axon.models.wow.mythicKeystoneProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.base.Playable;

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
