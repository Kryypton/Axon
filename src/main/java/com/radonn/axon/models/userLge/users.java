package com.radonn.axon.models.userLge;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class users {

    @Id
    private Long discordID;
    private String pseudo;
    private Date commingDate = new Date(System.currentTimeMillis());
    
    public Long getDiscordID() {
        return discordID;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Date getCommingDate() {
        return commingDate;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDiscordID(Long discordID) {
        this.discordID = discordID;
    }

    public void setCommingDate(Date commingDate) {
        this.commingDate = commingDate;
    }
}
