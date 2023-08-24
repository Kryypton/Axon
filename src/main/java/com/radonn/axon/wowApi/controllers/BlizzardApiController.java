package com.radonn.axon.wowApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.radonn.axon.wowApi.models.Character;
import com.radonn.axon.wowApi.models.achievements.Achievements;
import com.radonn.axon.wowApi.models.characterMedia.CharacterMedia;
import com.radonn.axon.wowApi.models.equipment.Equipment;
import com.radonn.axon.wowApi.models.guild.Guild;
import com.radonn.axon.wowApi.models.guild.GuildRoster;
import com.radonn.axon.wowApi.models.mythicKeystoneProfile.MythicKeystoneProfile;
import com.radonn.axon.wowApi.models.mythicKeystoneProfile.MythicKeystoneProfileSeason;
import com.radonn.axon.wowApi.models.specializations.PlayableSpecialization;
import com.radonn.axon.wowApi.services.battleNetOAuth.BattleNetOAuthClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/character")
public class BlizzardApiController {

    private final String profileNamespace = "profile-eu";
    private final String staticNamespace = "static-eu";
    private final String locale = "fr_FR";

    private WebClient webClient = WebClient.builder()
       .baseUrl("https://eu.api.blizzard.com")
       .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024)) // Augmentation de la limite du buffer
       .build();

    @GetMapping("/{realm}/{name}")
    public Character getCharacter(@PathVariable String realm, @PathVariable String name) {
        try {
            return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}", Character.class);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{realm}/{name}/equipment")
    public Equipment getEquipment(@PathVariable String realm, @PathVariable String name) {
        return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}/equipment", Equipment.class);
    }

    @GetMapping("/{id}")
    public PlayableSpecialization getPlayableSpecialisation(@PathVariable int id) {
        return fetchData(id, "/data/wow/playable-specialization/{id}", PlayableSpecialization.class);
    }

    @GetMapping("/{realm}/{name}/achievements")
    public Achievements getAchievements(@PathVariable String realm, @PathVariable String name) {
        return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}/achievements", Achievements.class);
    }

    @GetMapping("/{realm}/{name}/mythic-keystone-profile/season/{id}")
    public MythicKeystoneProfileSeason getMythicKeystoneProfileSeason(@PathVariable String realm, @PathVariable String name, @PathVariable int seasonIdIndex) {
		return fetchData(realm.toLowerCase(), name.toLowerCase(), seasonIdIndex, "/profile/wow/character/{realm}/{name}/mythic-keystone-profile/season/{id}", MythicKeystoneProfileSeason.class);
	}

    @GetMapping("/{realm}/{name}/character-media")
    public CharacterMedia getCharacterMedia(@PathVariable String realm, @PathVariable String name) {
		return fetchData(realm.toLowerCase(), name.toLowerCase(), "/profile/wow/character/{realm}/{name}/character-media", CharacterMedia.class);
	}

    @GetMapping("/{realm}/{name}/mythic-keystone-profile")
	public MythicKeystoneProfile getMythicKeystoneProfile(@PathVariable String realm, @PathVariable String name) {
		return fetchData(realm.toLowerCase(), name, "/profile/wow/character/{realm}/{name}/mythic-keystone-profile", MythicKeystoneProfile.class);
	}

    @GetMapping("/{realm}/{guild_name}")
    public Guild getGuild(@PathVariable String realm, @PathVariable String guild_name) {
        return fetchData(realm.toLowerCase(), guild_name.toLowerCase().replace(" ", "-"), "/data/wow/guild/{realm}/{guild_name}", Guild.class);
    }

    @GetMapping("/{realm}/{guild_name}/roster")
    public GuildRoster getGuildRoster(@PathVariable String realm, @PathVariable String guild_name) {
        return fetchData(realm.toLowerCase(), guild_name.toLowerCase().replace(" ", "-"), "/data/wow/guild/{realm}/{guild_name}/roster", GuildRoster.class);
    }

    private <T> T fetchData(String realm, String name, String path, Class<T> responseType) {
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
    }

    private <T> T fetchData(String realm, String name, int id, String path, Class<T> responseType) {
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
    }

    private <T> T fetchData(int id, String path, Class<T> responseType) {
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
    }
}