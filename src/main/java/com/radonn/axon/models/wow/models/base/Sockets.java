package com.radonn.axon.models.wow.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sockets {
    @JsonProperty("socket_type")
    private Descriptor socketType;
    private Item item;
    @JsonProperty("display_string")
    private String displayString;
    private Item media;

	public Descriptor getSocketType() {
		return socketType;
	}

	public Item getItem() {
		return item;
	}

	public String getDisplayString() {
		return displayString;
	}

	public Item getMedia() {
		return media;
	}

    public boolean isGemmed() {
        return item != null;
    }
}
