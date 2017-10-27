package rwcsim.factions.waiqar.upgrades.training;

import rwcsim.basicutils.stages.OnEmptyTray;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Training;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class LingeringDead implements Cost, Waiqar, Training {
    @Override
    public int price() {
        return 3;
    }
}
