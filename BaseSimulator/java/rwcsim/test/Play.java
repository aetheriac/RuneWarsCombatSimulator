package rwcsim.test;

import rwcsim.basicutils.Factions;
import rwcsim.basicutils.managers.UpgradeManager;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.LinkedList;
import java.util.List;

public class Play {
    public static void main(String[] args) {
        UpgradeManager.instance().getUpgradeList(Factions.WAIQAR, UpgradeTypes.Unique);
    }
}
