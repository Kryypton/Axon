package com.radonn.axon.wowApi.models.titles;

import com.radonn.axon.wowApi.models.base.Link;

public class Title {
    private Link key;
    private String name;
    private int id;
    
	public Link getKey() {
		return key;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
}
