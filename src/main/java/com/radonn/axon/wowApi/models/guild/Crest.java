package com.radonn.axon.wowApi.models.guild;

import com.radonn.axon.wowApi.models.base.ColorWithId;
import com.radonn.axon.wowApi.models.base.Shapes;

public class Crest {
    private Shapes border;
    private Shapes emblem;
    private ColorWithId background;

    public Shapes getBorder() {
        return border;
    }

    public Shapes getEmblem() {
        return emblem;
    }

    public ColorWithId getBackground() {
        return background;
    }
}
