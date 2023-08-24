package com.radonn.axon.wowApi.models.equipment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.wowApi.models.base.Effects;
import com.radonn.axon.wowApi.models.base.Items;
import com.radonn.axon.wowApi.models.base.Playable;

public class EquippedItemSets {
    @JsonProperty("item_set")
    private Playable itemSet;
    private List<Items> items;
    private List<Effects> effects;
    @JsonProperty("display_string")
    private String displayString;

    public Playable getItemSet() {
        return itemSet;
    }

    public List<Items> getItems() {
        return items;
    }

    public List<Effects> getEffects() {
        return effects;
    }

    public String getDisplayString() {
        return displayString;
    }
}
