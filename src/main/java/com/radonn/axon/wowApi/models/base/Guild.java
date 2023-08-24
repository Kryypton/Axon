package com.radonn.axon.wowApi.models.base;

public class Guild {
    private Link key;
    private String name;
    private int id;
    private Realm realm;
    private Descriptor faction;

	public Link getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Realm getRealm() {
		return realm;
	}

	public Descriptor getFaction() {
		return faction;
	}
}