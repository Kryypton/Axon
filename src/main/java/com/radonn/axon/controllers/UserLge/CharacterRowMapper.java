package com.radonn.axon.controllers.UserLge;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.radonn.axon.models.userLge.GuildCharacter;

public class CharacterRowMapper implements RowMapper<GuildCharacter> {
    
    @Override
    public GuildCharacter mapRow(ResultSet rs, int rowNum) throws SQLException {
        GuildCharacter character = new GuildCharacter();
        character.setCharacterID(rs.getLong("character_id"));
        character.setDiscordID(rs.getLong("discord_id"));
        character.setName(rs.getString("name"));
        character.setIsMain(rs.getBoolean("is_main"));
        character.setRole(rs.getInt("role"));
        return character;
    }
}
