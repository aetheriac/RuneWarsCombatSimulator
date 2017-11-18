package rwcsim.factions.daqan.upgrades.unique;

import rwcsim.basicutils.stages.OnMeleeAttack;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.daqan.LordHawthorne;
import rwcsim.factions.daqan.upgrades.Daqan;

public class SweepingStrikes implements Cost, Daqan, HeroSpecific<LordHawthorne>, Unique {
    @Override
    public LordHawthorne getHero() { return new LordHawthorne(); }
    @Override
    public int price() {
        return 5;
    }

    @Override
    public String getUpgradeName() {
        return "Sweeping Strikes";
    }
}
