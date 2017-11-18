package rwcsim.factions.waiqar.upgrades.unique;

import rwcsim.basicutils.actions.AddSurges;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.factions.waiqar.ArdusIxErebus;

public class ArdusFury implements HeroSpecific<ArdusIxErebus>, Unique, AddSurges {
    @Override
    public ArdusIxErebus getHero() { return new ArdusIxErebus(); }

    @Override
    public int getSurges() {
        return 1;
    }

    @Override
    public String getUpgradeName() {
        return "Ardus Fury";
    }
}
