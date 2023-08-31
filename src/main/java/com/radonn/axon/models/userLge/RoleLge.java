package com.radonn.axon.models.userLge;

public enum RoleLge {
    GM("Maitre de Guilde", "Bloc Premier",880141571864031302L  ,880141560065429514L, 0), 
    OFFICER("Officier", "Bloc Premier", 880141572186992711L ,880141560065429514L ,1), 
    MasterProffesion("Maitre de métiers", "Bloc Secondaire", 880141573126500352L, 880141570861576202L, 2), 
    RL("Raid leader", "Bloc Secondaire", 947169124461707275L, 880141570861576202L, 2), 
    Recruiter("Recruteur", "Bloc Secondaire", 898896164752097330L, 880141570861576202L, 2), 
    EventLeader("Organisateur d'évents", "Bloc Secondaire", 1026101174157455382L, 880141570861576202L, 2), 
    MEMBER("Membre", "Bloc Principale", 880141574414155846L, 880141571041951756L, 3), 
    NOUVEAU("Nouveau", "Bloc Principale", 880141573776621625L, 880141571041951756L, 4), 
    REROLL("Reroll", "Bloc Principale", 5),
    GUEST("Invité", "Bloc Invité", 880141574984568904L, 975029684637163561L), 
    NA("Non assigné", "Bloc N/A", 6);

    private String roleName;
    private String district;
    private long roleId;
    private long districtId;
    private int discriminant;

    private RoleLge(String roleName, String district, long roleId, long districtId, int discriminant) {
        this.roleName = roleName;
        this.district = district;
        this.discriminant = discriminant;
    }

    private RoleLge(String roleName, String district, long roleId, long districtId) {
        this.roleName = roleName;
        this.district = district;
        this.discriminant = -1;
    }

    private RoleLge(String roleName, String district, int discriminant) {
        this.roleName = roleName;
        this.district = district;
        this.discriminant = discriminant;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDistrict() {
        return district;
    }

    public long getRoleId() {
        return roleId;
    }

    public long getDistrictId() {
        return districtId;
    }

    public int getDiscriminant() {
        return discriminant;
    }

    public static RoleLge getRoleByDiscriminant(int discriminant) {
        for (RoleLge role : RoleLge.values()) {
            if (role.getDiscriminant() == discriminant) {
                return role;
            }
        }
        return null;
    }

    public static RoleLge getRoleByName(String roleName) {
        for (RoleLge role : RoleLge.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }
    
}
