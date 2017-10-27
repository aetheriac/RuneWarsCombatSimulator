package rwcsim.factions.waiqar.upgrades.artifact;

import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.upgrades.UpgradeSlot;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class TheDuskBlade implements Cost, UpgradeSlot, Artifact, Unique, Waiqar {
    @Override
    public int price() {
        return 8;
    }
}
