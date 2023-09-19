package com.radonn.axon.models.wow.encounters.raids;

import com.radonn.axon.models.wow.base.Descriptor;

public class modes {

    private Descriptor difficulty;
    private Descriptor status;
    private Progress progress;

    public Descriptor getDifficulty() {
        return difficulty;
    }

    public Descriptor getStatus() {
        return status;
    }

    public Progress getProgress() {
        return progress;
    }
}
