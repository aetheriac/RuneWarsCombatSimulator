package rwcsim.basicutils.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.Configuration;
import rwcsim.basicutils.Faction;

import java.util.HashMap;
import java.util.Map;

public class FactionManager {
    private Logger log = LogManager.getLogger(FactionManager.class);
    private static FactionManager factionManager = new FactionManager();
    Map<Faction, UnitManager> registeredFactions = new HashMap<>();
    String[] factionList=null;
    String[] leadingNullFactionList=null;

    private FactionManager() {
        Configuration.getInstance();
        registerFactions();
    }

    public static FactionManager instance() {
        return factionManager;
    }

    public void registerFactions() {
        try {
            for (Faction faction : Faction.values()) {
                log.info("Loading faction: "+ faction.getName());
                UnitManager unitManager = (UnitManager) Class.forName(faction.getUnitManager()).newInstance();
                registeredFactions.put(faction, unitManager);
            }
        } catch (ClassNotFoundException e) {
            log.error("Error loading unit manager.", e);
        } catch (IllegalAccessException e) {
            log.error("Error accessing unit manager.", e);
        } catch (InstantiationException e) {
            log.error("Error instantiating unit manager.", e);
        }
    }


    public String[] getFactionList() {
        if (null == factionList) {
            factionList = new String[registeredFactions.size()];
            int i = 0;
            for (Faction faction : registeredFactions.keySet()) {
                factionList[i++] = faction.getName();
            }
        }
        return factionList;
    }

    public String[] getLeadingNullFactionList() {
        if (null == leadingNullFactionList) {
            leadingNullFactionList = new String[registeredFactions.size()+1];
            leadingNullFactionList[0] = " - ";
            int i = 1;
            for (Faction faction : registeredFactions.keySet()) {
                leadingNullFactionList[i++] = faction.getName();
            }
        }
        return leadingNullFactionList;
    }

    public UnitManager getUnitManager(int index) {
        int vIndex = index - 1;
        Faction workingFaction = Faction.values()[vIndex];
        return registeredFactions.get(workingFaction);
    }

//    public UnitManager getUnitManager(String faction) {
//        Faction workingFaction = Faction.valueOf(faction);
//        return registeredFactions.get(workingFaction);
//    }

    public UnitManager getUnitManager(Faction faction) {
        return registeredFactions.get(faction);
    }

    public static void main(String[] args) {
        FactionManager.instance();
    }
}
