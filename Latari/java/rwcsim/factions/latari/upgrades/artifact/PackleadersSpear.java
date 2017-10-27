package rwcsim.factions.latari.upgrades.artifact;

import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.stages.OnMeleeAttack;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.latari.upgrades.Latari;

public class PackleadersSpear implements Cost, Latari, Artifact, Unique, Exhaustable {
    @Override
    public int price() {
        return 8;
    }
}
