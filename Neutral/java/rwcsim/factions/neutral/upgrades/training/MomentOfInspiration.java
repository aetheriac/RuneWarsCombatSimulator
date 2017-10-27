package rwcsim.factions.neutral.upgrades.training;

import rwcsim.basicutils.actions.AddDie;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.stages.OnAttack;
import rwcsim.basicutils.upgrades.Training;

public class MomentOfInspiration implements Cost, Training, Exhaustable, AddDie {
    @Override
    public int[] modifyDiePool() {
        return new int[]{0,0,1};
    }

    @Override
    public int price() {
        return 5;
    }
}
