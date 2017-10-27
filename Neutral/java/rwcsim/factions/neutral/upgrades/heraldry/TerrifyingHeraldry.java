package rwcsim.factions.neutral.upgrades.heraldry;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.BeginningEndPhase;
import rwcsim.basicutils.upgrades.Heraldry;

public class TerrifyingHeraldry implements Cost, Heraldry {
    @Override
    public int price() {
        return 5;
    }
}
