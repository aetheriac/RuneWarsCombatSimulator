package rwcsim.factions.neutral.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.TargetAttack;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;

public class HeartSeeker implements Artifact, Cost, Unique {
    @Override
    public int price() {
        return 10;
    }
}
