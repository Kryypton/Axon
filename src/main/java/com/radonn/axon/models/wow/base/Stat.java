package com.radonn.axon.models.wow.base;

public class Stat {
    private Descriptor type;
    private int value;
    private Display display;

	public Descriptor getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}

	public Display getDisplay() {
		return display;
	}
}
