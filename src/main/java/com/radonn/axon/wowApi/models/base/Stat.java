package com.radonn.axon.wowApi.models.base;

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
