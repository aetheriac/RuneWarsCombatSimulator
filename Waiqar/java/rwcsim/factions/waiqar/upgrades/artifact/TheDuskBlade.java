package rwcsim.factions.waiqar.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class TheDuskBlade implements Cost, Artifact, Unique, Waiqar {
    @Override
    public int price() {
        return 8;
    }

    @Override
    public String getUpgradeName() {
        return "The Dusk Blade";
    }
}
