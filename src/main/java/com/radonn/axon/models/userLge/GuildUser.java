package com.radonn.axon.models.userLge;

import java.sql.Timestamp;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

@Entity
@Table(name = "users")
public class GuildUser {

    @Id
    @Column(name = "discord_id")
    private Long discordID;
    @Column(name = "pseudo")
    private String pseudo;
    @Column(name = "comming_date")
    private Timestamp commingDate = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;
    @Column(name = "time_spend")
    private long timeSpend;

    public Long getDiscordID() {
        return discordID;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Timestamp getCommingDate() {
        return commingDate;
    }

    public long getTimeSpend() {
        return timeSpend;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDiscordID(Long discordID) {
        this.discordID = discordID;
    }

    public void setCommingDate(Timestamp commingDate) {
        this.commingDate = commingDate;
    }

    public void setTimeSpend(long timeSpend) {
        this.timeSpend = timeSpend;
    }
}
