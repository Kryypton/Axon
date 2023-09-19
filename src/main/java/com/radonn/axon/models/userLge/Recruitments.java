package com.radonn.axon.models.userLge;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recruitments")
public class Recruitments {

    @Id
    @Column(name = "RECRUITER_DISCORD_ID")
    private Long recruiterDiscordID;

    @Column(name = "CHARACTER_ID")
    private Long characterID;

    @Column(name = "CONVOCATION_DATE")
    private Timestamp convocationDate;

    @Column(name = "RECRUTEMENT_DATE")
    private Timestamp recrutementDate;

    public Long getRecruiterDiscordID() {
        return recruiterDiscordID;
    }

    public void setRecruiterDiscordID(Long recruiterDiscordID) {
        this.recruiterDiscordID = recruiterDiscordID;
    }

    public Long getCharacterID() {
        return characterID;
    }

    public void setCharacterID(Long characterID) {
        this.characterID = characterID;
    }

    public Timestamp getConvocationDate() {
        return convocationDate;
    }

    public void setConvocationDate(Timestamp convocationDate) {
        this.convocationDate = convocationDate;
    }

    public Timestamp getRecrutementDate() {
        return recrutementDate;
    }

    public void setRecrutementDate(Timestamp recrutementDate) {
        this.recrutementDate = recrutementDate;
    }

}
