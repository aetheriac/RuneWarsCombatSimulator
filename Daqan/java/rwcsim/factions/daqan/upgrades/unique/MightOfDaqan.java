package rwcsim.factions.daqan.upgrades.unique;

import rwcsim.basicutils.stages.WhileDefending;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.daqan.LordHawthorne;
import rwcsim.factions.daqan.upgrades.Daqan;

public class MightOfDaqan implements Cost, Daqan, HeroSpecific<LordHawthorne>, Unique {
    @Override
    public int price() {
        return 4;
    }
}
