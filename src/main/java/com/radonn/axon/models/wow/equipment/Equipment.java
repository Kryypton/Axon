package com.radonn.axon.models.wow.equipment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.base.Links;

public class Equipment {

	private static final List<String> ENCHANTED_SLOTS = Collections.unmodifiableList(
		Arrays.asList("MAIN_HAND", "BACK", "CHEST", "WAIST", "LEGS", "FEET", "WRIST", "HANDS", "FINGER_1", "FINGER_2")
	);

    @JsonProperty("_links")
    private Links links;
	
    private Character character;
    @JsonProperty("equipped_items")
    private List<EquippedItem> equippedItems;
    @JsonProperty("equipped_item_sets")
    private List<EquippedItemSets> equippedItemSets;

	public Links getLinks() {
		return links;
	}

	public Character getCharacter() {
		return character;
	}

	public List<EquippedItem> getEquippedItems() {
		return equippedItems;
	}

	public List<EquippedItemSets> getEquippedItemSets() {
		return equippedItemSets;
	}

	public Boolean isGemmed() {
		return equippedItems.stream()
			.filter(item -> item.getSockets() != null)
			.anyMatch(item -> item.getSockets().stream()
				.anyMatch(socket -> socket.isGemmed()));
	}

	public List<EquippedItem> getUnGemmedItems() {
		return equippedItems.stream()
			.filter(item -> item.getSockets() != null)
			.filter(item -> item.getSockets().stream()
				.anyMatch(socket -> !socket.isGemmed()))
			.collect(Collectors.toList());
	}

	public Boolean isEnchanted() {
		return equippedItems.stream()
			.filter(item -> item.getEnchantments() != null)
			.anyMatch(item -> Equipment.ENCHANTED_SLOTS.contains(item.getSlot().getName()));
	}

	public Boolean haveTwoSets() {
		return equippedItemSets.size() >= 2;
	}

	public Boolean haveFourSets() {
		return equippedItemSets.size() >= 4;
	}
}
