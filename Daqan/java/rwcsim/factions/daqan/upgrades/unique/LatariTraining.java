package rwcsim.factions.daqan.upgrades.unique;

import rwcsim.basicutils.stages.OnRangedAttack;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.daqan.KariWraithstalker;
import rwcsim.factions.daqan.upgrades.Daqan;

public class LatariTraining implements Cost, Daqan, HeroSpecific<KariWraithstalker>, Unique {
    @Override
    public int price() {
        return 6;
    }
}
