package com.radonn.axon.models.wow.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.exceptions.AchievementsNotFoundException;
import com.radonn.axon.exceptions.CharacterMediaNotFoundException;
import com.radonn.axon.exceptions.EquipmentNotFoundException;
import com.radonn.axon.exceptions.MythicKeystoneProfileNotFoundException;
import com.radonn.axon.exceptions.PlayableSpecializationNotFoundException;
import com.radonn.axon.models.wow.models.achievements.Achievements;
import com.radonn.axon.models.wow.models.base.*;
import com.radonn.axon.models.wow.models.characterMedia.CharacterMedia;
import com.radonn.axon.models.wow.models.equipment.Equipment;
import com.radonn.axon.models.wow.models.mythicKeystoneProfile.MythicKeystoneProfile;
import com.radonn.axon.models.wow.models.specializations.PlayableSpecialization;

public class Character {

    @JsonProperty("_links")
    private Links links;
    private long id;
    private String name;
    private Descriptor gender;
    private Descriptor faction;
    private Playable race;
    @JsonProperty("character_class")
    private Playable characterClass;
    @JsonProperty("active_spec")
    private Playable activeSpec;
    private Realm realm;
    private Guild guild;
    private int level;
    @JsonProperty("experience")
    private int experience;
    @JsonProperty("achievement_points")
    private int achievementPoints;
    @JsonProperty("last_login_timestamp")
    private long lastLoginTimestamp;
    @JsonProperty("average_item_level")
    private int averageItemLevel;
    @JsonProperty("equipped_item_level")
    private int equippedItemLevel;
    @JsonProperty("active_title")
    private Playable activeTitle;
    @JsonProperty("covenant_progress")
    private CovenantProgress covenantProgress;

	@SuppressWarnings("unused")
	private Link equipment;

	public Links getLinks() {
		return links;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Descriptor getGender() {
		return gender;
	}

	public Descriptor getFaction() {
		return faction;
	}

	public Playable getRace() {
		return race;
	}

	public Playable getCharacterClass() {
		return characterClass;
	}

	public Playable getActiveSpec() {
		return activeSpec;
	}

	public Realm getRealm() {
		return realm;
	}

	public Guild getGuild() {
		return guild;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public int getAchievementPoints() {
		return achievementPoints;
	}

	public long getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public int getAverageItemLevel() {
		return averageItemLevel;
	}

	public int getEquippedItemLevel() {
		return equippedItemLevel;
	}

	public Playable getActiveTitle() {
		return activeTitle;
	}

	public CovenantProgress getCovenantProgress() {
		return covenantProgress;
	}

	////

	public Equipment getEquipment() {
		try {
			return new BlizzardApiController().getEquipment(realm.getName().toLowerCase(), name.toLowerCase());
		} catch (EquipmentNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Achievements getAchievements() {
		try {
			return new BlizzardApiController().getAchievements(realm.getName().toLowerCase(), name.toLowerCase());
		} catch (AchievementsNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PlayableSpecialization getPlayableSpecialization() {
		try {
			return new BlizzardApiController().getPlayableSpecialisation(activeSpec.getId());
		} catch (PlayableSpecializationNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CharacterMedia getCharacterMedia() {
		try {
			return new BlizzardApiController().getCharacterMedia(realm.getName().toLowerCase(), name.toLowerCase());
		} catch (CharacterMediaNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MythicKeystoneProfile getMythicKeystoneProfile() {
		try {
			return new BlizzardApiController().getMythicKeystoneProfile(realm.getName().toLowerCase(), name.toLowerCase());
		} catch (MythicKeystoneProfileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
