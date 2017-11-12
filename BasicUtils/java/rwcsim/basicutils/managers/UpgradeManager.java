package rwcsim.basicutils.managers;

import com.google.common.collect.Multimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.Store;
import rwcsim.basicutils.Factions;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.*;

public class UpgradeManager {
    private Logger log = LogManager.getLogger(UpgradeManager.class);
    private static UpgradeManager upgradeManager = new UpgradeManager();

    public static UpgradeManager instance() {
        return upgradeManager;
    }

    public static String[] getUpgradeList(Factions faction, UpgradeTypes upgradeType) {
        Reflections neutralReflections = new Reflections("rwcsim.factions.neutral");
        Reflections factionReflections = new Reflections("rwcsim.factions."+ faction.getName().toLowerCase());

        Set<Class<?>> neutralUpgrades = neutralReflections.getSubTypesOf(upgradeType.getClazz());
        Set<Class<?>> factionUpgrades = factionReflections.getSubTypesOf(upgradeType.getClazz());

        List<String> upgrades = new LinkedList<>();

        for (Class c:factionUpgrades) {
            upgrades.add(c.getSimpleName());
        }

        for (Class c:neutralUpgrades) {
            upgrades.add(c.getSimpleName());
        }

        return upgrades.toArray(new String[0]);
    }
}
