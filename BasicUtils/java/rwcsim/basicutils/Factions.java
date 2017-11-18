package rwcsim.basicutils;

public enum Factions {
    NEUTRAL("Neutral", "rwcsim.factions.neutral.NeutralUnitManager"),
    DAQAN("Daqan", "rwcsim.factions.daqan.DaqanUnitManager"),
    LATARI("Latari", "rwcsim.factions.latari.LatariUnitManager"),
    UTHUK("Uthuk", "rwcsim.factions.uthuk.UthukUnitManager"),
    WAIQAR("Waiqar", "rwcsim.factions.waiqar.WaiqarUnitManager");

    private String name;
    private String unitManager;

    Factions(String name, String unitManager) {
        this.name = name;
        this.unitManager = unitManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitManager(String unitManager) {
        this.unitManager = unitManager;
    }

    public static Factions valueOfFromString(String chkname) {
        for(Factions f : Factions.values()) {
            if (f.name.compareTo(chkname) == 0) return f;
        }
        return null;
    }

    public String getUnitManager() {
        return unitManager;
    }
}
