package com.radonn.axon.models.wow.models.specializations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Loadouts {
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("talent_loadout_code")
    private String TalentLoadoutCode;

	public boolean isActive() {
		return isActive;
	}
    
	public String getTalentLoadoutCode() {
		return TalentLoadoutCode;
	}
}
