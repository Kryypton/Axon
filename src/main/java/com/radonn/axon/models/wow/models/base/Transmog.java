package com.radonn.axon.models.wow.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transmog {
    private Playable item;
    @JsonProperty("display_string")
    private String displayString;
    @JsonProperty("item_modified_appearance_id")
    private int itemModifiedAppearanceId;

	public Playable getItem() {
		return item;
	}

	public String getDisplayString() {
		return displayString;
	}
	
	public int getItemModifiedAppearanceId() {
		return itemModifiedAppearanceId;
	}
}
