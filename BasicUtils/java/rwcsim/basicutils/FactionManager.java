package rwcsim.basicutils;

import rwcsim.basicutils.managers.UnitManager;

import java.util.Map;

public class FactionManager {
    private static Map<String, Faction> factionMap;

    public static void loadFactions() {
        try {
            String[] factions = Configuration.getInstance().get("factions").split(",");
            for (String f : factions) {
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

    public static Faction getFaction(String name) {
        return factionMap.get(name);
    }
}
