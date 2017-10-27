package rwcsim.factions.neutral.upgrades.training;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.OnAttack;
import rwcsim.basicutils.upgrades.Training;

public class CloseQuarterTargeting implements Cost, Training {
    @Override
    public int price() {
        return 3;
    }
}
