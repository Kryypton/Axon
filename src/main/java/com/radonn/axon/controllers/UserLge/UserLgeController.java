package com.radonn.axon.controllers.UserLge;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radonn.axon.exceptions.UserLgeCharacterAlreadyExistException;
import com.radonn.axon.models.userLge.GuildCharacter;
import com.radonn.axon.models.userLge.RoleLge;
import com.radonn.axon.models.userLge.GuildUser;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.guild.Guild;
import com.radonn.axon.models.wow.guild.GuildRoster;

import net.dv8tion.jda.api.entities.User;

@RestController
@RequestMapping("/userLge")
public class UserLgeController {

    private final JdbcTemplate jdbcTemplate;

    public UserLgeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users/getById")
    public GuildUser getUserById(@RequestParam Long id) {
        String sql = "SELECT * FROM users WHERE discord_id = ?";
        GuildUser userLge = jdbcTemplate.queryForObject(sql, new GuildUserLgeRowMapper(), id);
        return userLge;
    }

    @PostMapping("/users/add")
    public GuildUser addUser(@RequestParam User user) {
        GuildUser userLge;
        try {
            userLge = new GuildUser();
            userLge.setDiscordID(user.getIdLong());
            userLge.setPseudo(user.getName());
            userLge.setTimeSpend(0L);
            userLge.setLastLoginDate(null);

            String sql = "INSERT INTO users (discord_id, pseudo, comming_date, time_spend) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, userLge.getDiscordID(), userLge.getPseudo(), userLge.getCommingDate(),
                    userLge.getTimeSpend());

            return userLge;
        } catch (DuplicateKeyException e) {
            return getUserById(user.getIdLong());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/users/delete")
    public void deleteUser(@RequestParam Long id) {
        String sql = "DELETE FROM users WHERE discord_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @PostMapping("/users/detectConnexion")
    public void detectConnexionUser(@RequestParam Long id) {
        String sql = "UPDATE users SET last_login_date = ? WHERE discord_id = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()), id);
    }

    @PostMapping("/users/detectDeconnexion")
    public void detectDeconnexionUser(@RequestParam Long id) {
        Timestamp lastLogin = getUserById(id).getLastLoginDate();
        if (lastLogin != null) {
            long timeSpend = getUserById(id).getTimeSpend()
                    + (Timestamp.valueOf(LocalDateTime.now()).getTime() - lastLogin.getTime());
            String sql = "UPDATE users SET time_spend = ? WHERE discord_id = ?";
            jdbcTemplate.update(sql, timeSpend, id);
        }
    }

    @PostMapping("/characters/add")
    public GuildCharacter addCharacter(@RequestParam Long discordID, @RequestParam Character character) {
        try {
            String sql = "INSERT INTO characters (character_id, discord_id, name, is_main, role) VALUES (?, ?, ?, null, null)";
            jdbcTemplate.update(sql, character.getId(), discordID, character.getName());
            return getCharacterById(character.getId());
        } catch (DuplicateKeyException e) {
            return getCharacterById(character.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/characters/addMain")
    public GuildCharacter addCharacterMain(@RequestParam Long discordID, @RequestParam Character character)
            throws UserLgeCharacterAlreadyExistException {
        try {
            String sql = "INSERT INTO characters (character_id, discord_id, name, is_main, role) VALUES (?, ?, ?, true, 4)";
            jdbcTemplate.update(sql, character.getId(), discordID, character.getName());
            return getCharacterById(character.getId());
        } catch (DuplicateKeyException e) {
            throw new UserLgeCharacterAlreadyExistException("Le personnage " + character.getName() + " existe déjà !");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/characters/delete")
    public void deleteCharacter(@RequestParam Long id) {
        String sql = "DELETE FROM characters WHERE character_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @PostMapping("/characters/deleteByDiscordID")
    public void deleteCharacterByDiscordID(@RequestParam Long discordID) {
        try {
            String sql = "DELETE FROM characters WHERE discord_id = ?";
            jdbcTemplate.update(sql, discordID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/characters/getById")
    public GuildCharacter getCharacterById(@RequestParam Long id) {
        String sql = "SELECT * FROM characters WHERE character_id = ?";
        GuildCharacter character = jdbcTemplate.queryForObject(sql, new CharacterRowMapper(), id);
        return character;
    }

    @GetMapping("/characters/getByDiscordId")
    public List<GuildCharacter> getCharacterByDiscordId(@RequestParam Long discordId) {
        String sql = "SELECT * FROM characters WHERE discord_id = ?";
        List<GuildCharacter> characters = jdbcTemplate.query(sql, new CharacterRowMapper(), discordId);
        return characters;
    }

    @GetMapping("/characters/main/getByDiscordId")
    public GuildCharacter getCharacterMainByDiscordId(@RequestParam Long discordId) {
        String sql = "SELECT * FROM characters WHERE discord_id = ? AND is_main = true";
        GuildCharacter characters = jdbcTemplate.queryForObject(sql, new CharacterRowMapper(), discordId);
        return characters;
    }

    @PostMapping("/characters/update")
    public boolean updateCharacter(@RequestParam Guild guild, @RequestParam GuildCharacter character) {
        try {
            String sql = "UPDATE characters SET is_main = ?, role = ? WHERE character_id = ?";
            GuildRoster gr = guild.getGuildRoster();
            int rank = gr.getMemberRank(character.getName());
            jdbcTemplate.update(sql,
                    (rank != RoleLge.getRoleByName("Reroll").getDiscriminant()
                            || rank != RoleLge.getRoleByName("N/A").getDiscriminant()),
                    rank, character.getCharacterID());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/characters/updateByDiscordID")
    public boolean updateCharacterByDiscordID(@RequestParam Guild guild, @RequestParam Long discord_id) {
        try {
            List<GuildCharacter> characters = getCharacterByDiscordId(discord_id);
            for (GuildCharacter character : characters) {
                System.out.println(character.getName());
                updateCharacter(guild, character);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/recruitements/valid")
    public boolean validRecruitment(@RequestParam Long characterID,
            @RequestParam Timestamp entretienDate) {
        try {
            String sql = "UPDATE recruitments SET recrutement_date = ? WHERE character_id = ?";
            jdbcTemplate.update(sql, entretienDate, characterID);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/recruitements/convoc")
    public boolean convocRecruitment(@RequestParam Long recruiterDiscordID, @RequestParam Long characterID,
            @RequestParam Timestamp convocationDate) {
        try {

            String sql = "INSERT INTO recruitments (RECRUITER_DISCORD_ID, CHARACTER_ID, CONVOCATION_DATE, recrutement_date) VALUES (?, ?, ?, null)";
            jdbcTemplate.update(sql, recruiterDiscordID, characterID, convocationDate);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
