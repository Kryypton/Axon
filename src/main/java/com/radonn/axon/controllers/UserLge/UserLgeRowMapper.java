package com.radonn.axon.controllers.UserLge;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.radonn.axon.models.userLge.users;

public class UserLgeRowMapper implements RowMapper<users> {

    @Override
    public users mapRow(ResultSet rs, int rowNum) throws SQLException {
        users userLge = new users();
        userLge.setDiscordID(rs.getLong("discord_id"));
        userLge.setPseudo(rs.getString("pseudo"));
        userLge.setCommingDate(rs.getTimestamp("comming_date"));
        userLge.setLastLoginDate(rs.getTimestamp("last_login_date"));
        userLge.setTimeSpend(rs.getLong("time_spend"));
        return userLge;
    }
}
