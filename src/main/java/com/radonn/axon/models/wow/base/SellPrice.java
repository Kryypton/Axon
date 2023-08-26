package com.radonn.axon.models.wow.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SellPrice {
    private int value;
    @JsonProperty("display_strings")
    private DisplayString displayString;

	public int getValue() {
		return value;
	}
	
	public DisplayString getDisplayString() {
		return displayString;
	}
}
