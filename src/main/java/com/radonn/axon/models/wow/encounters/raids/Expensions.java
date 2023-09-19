package com.radonn.axon.models.wow.encounters.raids;

import java.util.List;

import com.radonn.axon.models.wow.base.Playable;

public class Expensions {

    private Playable expansion;
    private List<Instances> instances;

    public Playable getExpansion() {
        return expansion;
    }

    public List<Instances> getInstances() {
        return instances;
    }
}
