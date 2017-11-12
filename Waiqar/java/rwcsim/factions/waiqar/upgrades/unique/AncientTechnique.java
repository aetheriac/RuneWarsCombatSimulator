package rwcsim.factions.waiqar.upgrades.unique;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.waiqar.ArdusIxErebus;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class AncientTechnique implements Cost, Waiqar, HeroSpecific<ArdusIxErebus>, Unique, AddAbility<Precise> {
    @Override
    public int price() {
        return 2;
    }

    @Override
    public Precise getAbility() {
        return new Precise(1);
    }

    @Override
    public String getUpgradeName() {
        return "Ancient Technique";
    }
}
