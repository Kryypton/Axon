package com.radonn.axon.models.wow.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enchantement {
    @JsonProperty("display_string")
    private String display_string;
    @JsonProperty("enchantment_id")
    private int enchantment_id;
    @JsonProperty("enchantment_slot")
    private Item enchantment_slot;

	public String getDisplay_string() {
		return display_string;
	}

	public int getEnchantment_id() {
		return enchantment_id;
	}

	public Item getEnchantment_slot() {
		return enchantment_slot;
	}
}
