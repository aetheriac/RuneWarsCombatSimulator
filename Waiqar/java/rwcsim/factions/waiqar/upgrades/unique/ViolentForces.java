package rwcsim.factions.waiqar.upgrades.unique;

import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.stages.OnRangedAttack;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.waiqar.AnkaurMaro;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class ViolentForces implements Cost, Waiqar,HeroSpecific<AnkaurMaro>, Unique, AddAbility<Brutal> {

    @Override
    public int price() {
        return 6;
    }

    @Override
    public Brutal getAbility() {
        return new Brutal(1);
    }
}
