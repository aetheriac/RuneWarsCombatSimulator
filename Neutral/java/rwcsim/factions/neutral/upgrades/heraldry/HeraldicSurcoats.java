package rwcsim.factions.neutral.upgrades.heraldry;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.OnMoraleTest;
import rwcsim.basicutils.upgrades.Heraldry;

public class HeraldicSurcoats implements Cost, Heraldry {
    @Override
    public int price() {
        return 5;
    }
}
