package com.radonn.axon.controllers.UserLge;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @GetMapping("/users/getById")
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
            userLge.setTimeSpend(0L);
            userLge.setLastLoginDate(null);
            
            String sql = "INSERT INTO users (discord_id, pseudo, comming_date, time_spend) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, userLge.getDiscordID(), userLge.getPseudo(), userLge.getCommingDate(), userLge.getTimeSpend());
            
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

    @PostMapping("/users/detectConnexion")
    public void detectConnexionUser(@RequestParam Long id) {
        String sql = "UPDATE users SET last_login_date = ? WHERE discord_id = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()) ,id);
    }

    @PostMapping("/users/detectDeconnexion")
    public void detectDeconnexionUser(@RequestParam Long id) {
        Timestamp lastLogin = getUserById(id).getLastLoginDate();
        if (lastLogin != null) {
            long timeSpend = getUserById(id).getTimeSpend() + (Timestamp.valueOf(LocalDateTime.now()).getTime() - lastLogin.getTime());
            String sql = "UPDATE users SET time_spend = ? WHERE discord_id = ?";
            jdbcTemplate.update(sql, timeSpend, id);
        }
    }
}
