package com.radonn.axon.controllers.UserLge;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radonn.axon.models.userLge.users;

import net.dv8tion.jda.api.entities.User;

@RestController
@RequestMapping("/userLge")
public class UserLgeController {

    private final JdbcTemplate jdbcTemplate;

    public UserLgeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users/all")
    public users getUserById(@RequestParam Long id) {
        String sql = "SELECT * FROM users WHERE discord_id = ?";
        users userLge = jdbcTemplate.queryForObject(sql, new UserLgeRowMapper(), id);
        return userLge;
    }

    @PostMapping("/users/add")
    public users addUser(@RequestParam User user) {
        users userLge;
        try {
            userLge = new users();
            userLge.setDiscordID(user.getIdLong());
            userLge.setPseudo(user.getName());
            
            String sql = "INSERT INTO users (discord_id, pseudo, comming_date) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, userLge.getDiscordID(), userLge.getPseudo(), userLge.getCommingDate());
            
            return userLge;
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/users/delete")
    public void deleteUser(@RequestParam Long id) {
        String sql = "DELETE FROM users WHERE discord_id = ?";
        jdbcTemplate.update(sql, id);
    }


}
