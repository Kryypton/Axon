package com.radonn.axon.models.wow.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {
    private Item item;
    @JsonProperty("is_equipped")
    private Boolean isEquipped;
    
	public Item getItem() {
		return item;
	}

	public Boolean getIsEquipped() {
		return isEquipped;
	}
}
