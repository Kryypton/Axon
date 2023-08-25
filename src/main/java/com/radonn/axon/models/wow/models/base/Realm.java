package com.radonn.axon.models.wow.models.base;

public class Realm {
    private Link key;
    private String name;
    private int id;
    private String slug;

	public Link getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getSlug() {
		return slug;
	}
}