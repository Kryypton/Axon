package com.radonn.axon.wowApi.models.titles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.base.Link;

public class ActiveTitle {
    private Link key;
    private String name;
    private int id;
    @JsonProperty("display_string")
    private String displayString;

	public Link getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDisplayString() {
		return displayString;
	}
}
