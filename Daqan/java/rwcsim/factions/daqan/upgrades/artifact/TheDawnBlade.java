package rwcsim.factions.daqan.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.factions.daqan.upgrades.Daqan;

public class TheDawnBlade implements UpgradeSlot, Artifact, Unique, Daqan, Cost {
    @Override
    public int price() {
        return 10;
    }
}
