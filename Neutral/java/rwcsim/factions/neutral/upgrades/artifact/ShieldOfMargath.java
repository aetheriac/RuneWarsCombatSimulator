package rwcsim.factions.neutral.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.AfterDefending;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;

public class ShieldOfMargath implements Artifact, Cost, Unique {
    @Override
    public int price() {
        return 6;
    }
}
