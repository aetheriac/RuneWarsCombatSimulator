package rwcsim.basicutils;

import rwcsim.basicutils.managers.UnitManager;

import java.util.ArrayList;
import java.util.List;

public class Faction {
//    DAQAN("Daqan", "rwcsim.factions.daqan.DaqanUnitManager"),
//    LATARI("Latari", "rwcsim.factions.latari.LatariUnitManager"),
//    UTHUK("Uthuk", "rwcsim.factions.uthuk.UthukUnitManager"),
//    WAIQAR("Waiqar", "rwcsim.factions.waiqar.WaiqarUnitManager");

    private static Faction[] factions = new Faction[4];

    private String name;
    private UnitManager unitManager;

//    Faction(String name, String unitManager) {
//        this.name = name;
//        this.unitManager = unitManager;
//    }


    public Faction(String name, UnitManager unitManager) {
        this.name = name;
        this.unitManager = unitManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static Faction[] values() {
        return factions;
    }

    public static Faction valueOfFromString(String chkname) {
        for(Faction f : Faction.values()) {
            if (f.name.compareTo(chkname) == 0) return f;
        }
        return null;
    }

    public UnitManager getUnitManager() {
        return unitManager;
    }
}
