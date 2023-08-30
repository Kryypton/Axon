package com.radonn.axon.models.userLge;

public enum RoleLge {
    GM("Maitre de Guilde", "Bloc Premier", 0), OFFICER("Officier", "Bloc Premier", 1), MasterProffesion("Maitre de métiers", "Bloc Secondaire", 2), RL("Raid leader", "Bloc Secondaire", 2), Recruiter("Recruteur", "Bloc Secondaire", 2), EventLeader("Organisateur d'évents", "Bloc Secondaire", 2), MEMBER("Membre", "Bloc Principale", 3), NOUVEAU("Nouveau", "Bloc Principale", 4), GUEST("Invité", "Bloc Invité"), NA("Non assigné", "Bloc N/A", 6);

    private String roleName;
    private String district;
    private int order;

    private RoleLge(String roleName, String district, int order) {
        this.roleName = roleName;
        this.district = district;
        this.order = order;
    }

    private RoleLge(String roleName, String district) {
        this.roleName = roleName;
        this.district = district;
        this.order = -1;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDistrict() {
        return district;
    }

    public int getOrder() {
        return order;
    }
}
