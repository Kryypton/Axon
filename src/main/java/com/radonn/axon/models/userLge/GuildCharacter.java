package com.radonn.axon.models.userLge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.exceptions.CharacterNotFoundException;

@Entity
@Table(name = "GuildCharacters")
public class GuildCharacter {

    @Id
    @Column(name = "character_id")
    private Long characterID;
    @Column(name = "discord_id")
    private Long discordID;
    @Column(name = "name")
    private String name;
    @Column(name = "is_main")
    private Boolean isMain;
    @Column(name = "role")
    private int role;
    
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

    public int getRole() {
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

    public void setRole(int role) {
        this.role = role;
    }

    public RoleLge getRoleLge() {
        return RoleLge.getRoleByDiscriminant(this.getRole());
    }

    public com.radonn.axon.models.wow.Character getCharacter() throws CharacterNotFoundException {
        return AxonApplication.BnetCtrl.getCharacter("hyjal", this.getName());
    }
}
