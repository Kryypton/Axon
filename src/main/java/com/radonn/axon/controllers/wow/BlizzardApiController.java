package com.radonn.axon.controllers.wow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.radonn.axon.exceptions.AchievementsNotFoundException;
import com.radonn.axon.exceptions.CharacterMediaNotFoundException;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.exceptions.EquipmentNotFoundException;
import com.radonn.axon.exceptions.FetchDataException;
import com.radonn.axon.exceptions.GuildNotFoundException;
import com.radonn.axon.exceptions.GuildRosterNotFoundException;
import com.radonn.axon.exceptions.MythicKeystoneProfileNotFoundException;
import com.radonn.axon.exceptions.MythicKeystoneProfileSeasonNotFoundException;
import com.radonn.axon.exceptions.PlayableSpecializationNotFoundException;
import com.radonn.axon.exceptions.RaidCompletitionNotFoundException;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.achievements.Achievements;
import com.radonn.axon.models.wow.characterMedia.CharacterMedia;
import com.radonn.axon.models.wow.encounters.Raids;
import com.radonn.axon.models.wow.equipment.Equipment;
import com.radonn.axon.models.wow.guild.Guild;
import com.radonn.axon.models.wow.guild.GuildRoster;
import com.radonn.axon.models.wow.mythicKeystoneProfile.MythicKeystoneProfile;
import com.radonn.axon.models.wow.mythicKeystoneProfile.MythicKeystoneProfileSeason;
import com.radonn.axon.models.wow.specializations.PlayableSpecialization;
import com.radonn.axon.services.battleNetOAuth.BattleNetOAuthClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/character")
public class BlizzardApiController {

    private final String profileNamespace = "profile-eu";
    private final String staticNamespace = "static-eu";
    private final String locale = "fr_FR";

    private WebClient webClient = WebClient.builder()
            .baseUrl("https://eu.api.blizzard.com")
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024)) // Augmentation de la limite
                                                                                           // du buffer
            .build();

    @GetMapping("/{realm}/{name}")
    public Character getCharacter(@PathVariable String realm, @PathVariable String name)
            throws CharacterNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}",
                    Character.class);
        } catch (FetchDataException e) {
            throw new CharacterNotFoundException("Personnage " + realm + "/" + name + " non trouvé dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/equipment")
    public Equipment getEquipment(@PathVariable String realm, @PathVariable String name)
            throws EquipmentNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}/equipment",
                    Equipment.class);
        } catch (FetchDataException e) {
            throw new EquipmentNotFoundException("Équipement non trouvé dans l'API.");
        }
    }

    @GetMapping("/{id}")
    public PlayableSpecialization getPlayableSpecialisation(@PathVariable int id)
            throws PlayableSpecializationNotFoundException {
        try {
            return fetchData(id, "/data/wow/playable-specialization/{id}", PlayableSpecialization.class);
        } catch (FetchDataException e) {
            throw new PlayableSpecializationNotFoundException("Spécialisation non trouvée dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/encounters/raids")
    public Raids getRaidCompletition(@PathVariable String realm, @PathVariable String name)
            throws RaidCompletitionNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(),
                    "/profile/wow/character/{realm}/{name}/encounters/raids", Raids.class);
        } catch (FetchDataException e) {
            e.printStackTrace();
            throw new RaidCompletitionNotFoundException("Raid List non trouvé dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/achievements")
    public Achievements getAchievements(@PathVariable String realm, @PathVariable String name)
            throws AchievementsNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(),
                    "/profile/wow/character/{realm}/{name}/achievements", Achievements.class);
        } catch (FetchDataException e) {
            throw new AchievementsNotFoundException("Hauts faits non trouvés dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/mythic-keystone-profile/season/{id}")
    public MythicKeystoneProfileSeason getMythicKeystoneProfileSeason(@PathVariable String realm,
            @PathVariable String name, @PathVariable int seasonIdIndex)
            throws MythicKeystoneProfileSeasonNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(), seasonIdIndex,
                    "/profile/wow/character/{realm}/{name}/mythic-keystone-profile/season/{id}",
                    MythicKeystoneProfileSeason.class);
        } catch (FetchDataException e) {
            throw new MythicKeystoneProfileSeasonNotFoundException("Saison non trouvée dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/character-media")
    public CharacterMedia getCharacterMedia(@PathVariable String realm, @PathVariable String name)
            throws CharacterMediaNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(),
                    "/profile/wow/character/{realm}/{name}/character-media", CharacterMedia.class);
        } catch (FetchDataException e) {
            throw new CharacterMediaNotFoundException("Média non trouvé dans l'API.");
        }
    }

    @GetMapping("/{realm}/{name}/mythic-keystone-profile")
    public MythicKeystoneProfile getMythicKeystoneProfile(@PathVariable String realm, @PathVariable String name)
            throws MythicKeystoneProfileNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(),
                    "/profile/wow/character/{realm}/{name}/mythic-keystone-profile", MythicKeystoneProfile.class);
        } catch (FetchDataException e) {
            throw new MythicKeystoneProfileNotFoundException("Profil de clé mythique non trouvé dans l'API.");
        }
    }

    @GetMapping("/{realm}/{guild_name}")
    public Guild getGuild(@PathVariable String realm, @PathVariable String guild_name) throws GuildNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), guild_name.toLowerCase().replace(" ", "-"),
                    "/data/wow/guild/{realm}/{guild_name}", Guild.class);
        } catch (FetchDataException e) {
            throw new GuildNotFoundException("Guilde non trouvée dans l'API.");
        }
    }

    @GetMapping("/{realm}/{guild_name}/roster")
    public GuildRoster getGuildRoster(@PathVariable String realm, @PathVariable String guild_name)
            throws GuildRosterNotFoundException {
        try {
            return fetchData(realm.toLowerCase(), guild_name.toLowerCase().replace(" ", "-"),
                    "/data/wow/guild/{realm}/{guild_name}/roster", GuildRoster.class);
        } catch (FetchDataException e) {
            throw new GuildRosterNotFoundException("Roster de guilde non trouvé dans l'API.");
        }
    }

    private <T> T fetchData(String realm, String name, String path, Class<T> responseType) throws FetchDataException {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(path)
                            .queryParam("namespace", profileNamespace)
                            .queryParam("locale", locale)
                            .queryParam("access_token", BattleNetOAuthClient.getToken())
                            .build(realm, name))
                    .retrieve()
                    .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("Erreur lors de l'appel à l'API Blizzard.")))
                    .bodyToMono(responseType)
                    .block();
        } catch (RuntimeException e) {
            throw new FetchDataException("Erreur lors de la récupération des données: ", e);
        }
    }

    private <T> T fetchData(String realm, String name, int id, String path, Class<T> responseType)
            throws FetchDataException {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(path)
                            .queryParam("namespace", profileNamespace)
                            .queryParam("locale", locale)
                            .queryParam("access_token", BattleNetOAuthClient.getToken())
                            .build(realm, name, id))
                    .retrieve()
                    .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("Erreur lors de l'appel à l'API Blizzard.")))
                    .bodyToMono(responseType)
                    .block();
        } catch (RuntimeException e) {
            throw new FetchDataException("Erreur lors de la récupération des données: ", e);
        }
    }

    private <T> T fetchData(int id, String path, Class<T> responseType) throws FetchDataException {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(path)
                            .queryParam("namespace", staticNamespace)
                            .queryParam("locale", locale)
                            .queryParam("access_token", BattleNetOAuthClient.getToken())
                            .build(id))
                    .retrieve()
                    .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("Erreur lors de l'appel à l'API Blizzard.")))
                    .bodyToMono(responseType)
                    .block();
        } catch (RuntimeException e) {
            throw new FetchDataException("Erreur lors de la récupération des données: ", e);
        }
    }
}