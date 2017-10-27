package rwcsim.factions.neutral.upgrades.heraldry;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.OnReceivingBane;
import rwcsim.basicutils.upgrades.Heraldry;

public class CursedSignets implements Cost, Heraldry {
    @Override
    public int price() {
        return 2;
    }
}
