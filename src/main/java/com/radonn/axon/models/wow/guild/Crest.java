package com.radonn.axon.models.wow.guild;

import com.radonn.axon.models.wow.base.ColorWithId;
import com.radonn.axon.models.wow.base.Shapes;

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
