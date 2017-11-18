package rwcsim.basicutils.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import rwcsim.basicutils.Factions;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.HeroUpgrade;
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

    public static String[] getHeroUpgradeList(Factions faction, UpgradeTypes upgradeType, String hero) {
//        Reflections neutralReflections = new Reflections("rwcsim.factions.neutral");
//        Reflections factionReflections = new Reflections("rwcsim.factions."+ faction.getName().toLowerCase());
//
//        Set<Class<?>> neutralUpgrades = neutralReflections.getSubTypesOf(upgradeTypes.getClazz());
//        Set<Class<?>> factionUpgrades = factionReflections.getSubTypesOf(upgradeTypes.getClazz());
////        Set<Class<?>> heroUpgrades    = factionReflections.getSubTypesOf(heroSpecific.getHero().getClass());
//
        List<String> upgrades = Arrays.asList(getUpgradeList(faction, upgradeType));

        




        return upgrades.toArray(new String[0]);
    }
}
