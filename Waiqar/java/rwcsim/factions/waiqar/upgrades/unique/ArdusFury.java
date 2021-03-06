package rwcsim.factions.waiqar.upgrades.unique;

import rwcsim.basicutils.actions.AddSurges;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.factions.waiqar.ArdusIxErebus;

public class ArdusFury implements HeroSpecific<ArdusIxErebus>, Unique, AddSurges {

    @Override
    public int getSurges() {
        return 1;
    }
}
