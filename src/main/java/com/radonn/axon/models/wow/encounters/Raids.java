package com.radonn.axon.models.wow.encounters;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.base.Links;
import com.radonn.axon.models.wow.encounters.raids.Expensions;

public class Raids {

    @JsonProperty("_links")
    private Links links;
    @JsonProperty("expansions")
    private List<Expensions> expansions;

    public Links getLinks() {
        return links;
    }

    public List<Expensions> getExpensions() {
        return expansions;
    }

    public Expensions getCurrentExpansions() {
        for (Expensions expansion : expansions) {
            if (expansion.getExpansion().getName().equals("Current Season")) {
                return expansion;
            }
        }
        return null;
    }

    public Expensions getLastExpensions() {
        return expansions.get(expansions.size() - 2);
    }
}
