package com.radonn.axon.models.wow.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Display {
    @JsonProperty("display_string")
    private String displayString;
    @JsonProperty("color")
    private Color color;

	public String getDisplayString() {
		return displayString;
	}
	
	public Color getColor() {
		return color;
	}
}
