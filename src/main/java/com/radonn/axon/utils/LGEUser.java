package com.radonn.axon.utils;

import java.util.ArrayList;

import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.models.wow.Character;

public class LGEUser {

    public static final String GUILD_REALM = "Hyjal";

    /// Api
    private BlizzardApiController blizzardApiController = new BlizzardApiController();
    private String mainCharacterName;
    private ArrayList<String> altCharacterNames = new ArrayList<String>();
    
    /// discord
    private long discordId;
    /*private long lastLoginTimestamp;
    private long timeSpendInGuild;*/

    public LGEUser(Long discordId, String mainCharacterName, ArrayList<String> altCharacterNames) {
        this.discordId = discordId;
        this.mainCharacterName = mainCharacterName;
        this.altCharacterNames = altCharacterNames;
    }

    public LGEUser(Long discordId, String mainCharacterName) {
        this(discordId, mainCharacterName, null);
    }

    public BlizzardApiController getBlizzardApiController() {
        return blizzardApiController;
    }

    public String getMainCharacterName() {
        return mainCharacterName;
    }

    public ArrayList<String> getAltCharacterNames() {
        return altCharacterNames;
    }

    public long getDiscordId() {
        return discordId;
    }

    public Character getBuildedMain() throws CharacterNotFoundException {
        return blizzardApiController.getCharacter(GUILD_REALM, mainCharacterName);
    }

    public ArrayList<Character> getBuildedAlts() throws CharacterNotFoundException {
        ArrayList<Character> alts = new ArrayList<Character>();
        for (String altName : altCharacterNames) {
            alts.add(blizzardApiController.getCharacter(GUILD_REALM, altName));
        }
        return alts;
    }
}
