package rwcsim.factions.latari.upgrades.unique;

import rwcsim.basicutils.modifiers.AddStages;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.latari.AlianaOfSummersong;
import rwcsim.factions.latari.upgrades.Latari;

public class AmbushPredator implements Cost, Latari, HeroSpecific<AlianaOfSummersong>, Unique, AddStages {
    @Override
    public AlianaOfSummersong getHero() { return new AlianaOfSummersong(); }
    @Override
    public int price() {
        return 3;
    }

    @Override
    public void registerStages(Unit unit) {
//        <WhileDefending, OnMeleeAttack>
    }

    @Override
    public String getUpgradeName() {
        return "Ambush Predator";
    }
}
