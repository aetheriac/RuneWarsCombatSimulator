package rwcsim.factions.neutral.upgrades.artifact;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.AddStages;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.stages.ModifyDice;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.Artifact;
import rwcsim.basicutils.upgrades.Unique;

public class FortunasDice implements Cost, Artifact, AddStages, Unique, Exhaustable {
    @Override
    public int price() {
        return 6;
    }


    @Override
    public void registerStages(Unit unit) {

    }
}
