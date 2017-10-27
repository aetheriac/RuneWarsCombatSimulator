package rwcsim.factions.waiqar.upgrades.training;

import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Training;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class CombatIngenuity implements Cost, Waiqar, Training {
    @Override
    public int price() {
        return 6;
    }
}
