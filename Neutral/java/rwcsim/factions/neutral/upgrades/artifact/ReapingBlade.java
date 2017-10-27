package rwcsim.factions.neutral.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.SpendMortalStrikes;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;

public class ReapingBlade implements Artifact, Cost, Unique {
    @Override
    public int price() {
        return 4;
    }
}
