package com.radonn.axon.models.userLge;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class users {

    @Id
    private Long discordID;
    private String pseudo;
    private Timestamp commingDate = Timestamp.valueOf(LocalDateTime.now());
    
    public Long getDiscordID() {
        return discordID;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Timestamp getCommingDate() {
        return commingDate;
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
}
