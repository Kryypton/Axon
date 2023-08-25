package com.radonn.axon.models.wow.models.guild;

import com.radonn.axon.models.wow.models.base.ColorWithId;
import com.radonn.axon.models.wow.models.base.Shapes;

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
