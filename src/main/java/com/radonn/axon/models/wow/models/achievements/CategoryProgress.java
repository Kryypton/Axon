package com.radonn.axon.models.wow.models.achievements;

import com.radonn.axon.models.wow.models.base.Playable;

public class CategoryProgress {
    private Playable category;
    private int quantity;
    private int points;

	public Playable getCategory() {
		return category;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPoints() {
		return points;
	}
}
