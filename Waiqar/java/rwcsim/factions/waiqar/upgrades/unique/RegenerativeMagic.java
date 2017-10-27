package rwcsim.factions.waiqar.upgrades.unique;

import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.waiqar.AnkaurMaro;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class RegenerativeMagic implements Cost, Waiqar,HeroSpecific<AnkaurMaro>, Unique {
    @Override
    public int price() {
        return 5;
    }
}
