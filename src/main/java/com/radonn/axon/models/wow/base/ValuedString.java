package com.radonn.axon.models.wow.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValuedString {
    private int value;
    @JsonProperty("display_string")
    private String  display_string;

	public int getValue() {
		return value;
	}
	
	public String getDisplay_string() {
		return display_string;
	}  
}
