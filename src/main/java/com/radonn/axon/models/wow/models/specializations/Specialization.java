package com.radonn.axon.models.wow.models.specializations;

import java.util.List;

import com.radonn.axon.models.wow.models.base.Playable;

public class Specialization {
    private Playable specialization;
    private List<Loadouts> loadouts;

	public Playable getSpecialization() {
		return specialization;
	}
    
	public List<Loadouts> getLoadouts() {
		return loadouts;
	}
}
