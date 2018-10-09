package rwcsim.basicutils.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.Configuration;
import rwcsim.basicutils.Faction;

import java.util.HashMap;
import java.util.Map;

public class FactionManager {
    private static Logger log = LogManager.getLogger(FactionManager.class);
    private static FactionManager factionManager = new FactionManager();
//    Map<Faction, UnitManager> registeredFactions = new HashMap<>();
    static Map<String, Faction> factionMap = new HashMap<>();
    String[] factionList=null;
    String[] leadingNullFactionList=null;

    private FactionManager() {
        Configuration.getInstance();
        registerFactions();
    }

    public static FactionManager instance() {
        return factionManager;
    }

//    public void registerFactions() {
//        try {
//            for (Faction faction : Faction.values()) {
//                log.info("Loading faction: "+ faction.getName());
//                UnitManager unitManager = (UnitManager) Class.forName(faction.getUnitManager()).newInstance();
//                registeredFactions.put(faction, unitManager);
//            }
//        } catch (ClassNotFoundException e) {
//            log.error("Error loading unit manager.", e);
//        } catch (IllegalAccessException e) {
//            log.error("Error accessing unit manager.", e);
//        } catch (InstantiationException e) {
//            log.error("Error instantiating unit manager.", e);
//        }
//    }


    public String[] getFactionList() {
        if (null == factionList) {
            factionList = new String[factionMap.size()];
            int i = 0;
            for (String faction : factionMap.keySet()) {
                factionList[i++] = faction;
            }
        }
        return factionList;
    }

    public String[] getLeadingNullFactionList() {
        if (null == leadingNullFactionList) {
            leadingNullFactionList = new String[factionMap.size()+1];
            leadingNullFactionList[0] = " - ";
            int i = 1;
            for (String faction : factionMap.keySet()) {
                leadingNullFactionList[i++] = faction;
            }
        }
        return leadingNullFactionList;
    }

    public UnitManager getUnitManager(int index) {
        int vIndex = index - 1;
        String factionName = factionList[vIndex];
        return factionMap.get(factionName).getUnitManager();
    }

//    public UnitManager getUnitManager(String faction) {
//        Faction workingFaction = Faction.valueOf(faction);
//        return registeredFactions.get(workingFaction);
//    }

    public UnitManager getUnitManager(String faction) {
        return factionMap.get(faction.toUpperCase()).getUnitManager();
    }

    public static void main(String[] args) {
        FactionManager.instance();
    }


    public static void registerFactions() {
        try {
            String[] factions = Configuration.getInstance().get("factions").split(",");
            for (String f : factions) {
                log.info("Register faction: "+ f);
                factionMap.put(f.toUpperCase(), new Faction(f.toUpperCase(), (UnitManager) Class.forName(Configuration.getInstance().get("factions." + f)).newInstance()));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public static  Faction getFaction(String name) {
        return factionMap.get(name);
    }
}
