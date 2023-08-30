package com.radonn.axon.models.userLge;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "characters")
public class characters {

    @Id
    private Long characterID;
    private Long discordID;
    private String name;
    private Boolean isMain;
    private SmallIntJdbcType role;
    
    public Long getCharacterID() {
        return characterID;
    }

    public Long getDiscordID() {
        return discordID;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public SmallIntJdbcType getRole() {
        return role;
    }

    public void setCharacterID(Long characterID) {
        this.characterID = characterID;
    }

    public void setDiscordID(Long discordID) {
        this.discordID = discordID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public void setRole(SmallIntJdbcType role) {
        this.role = role;
    }
}
