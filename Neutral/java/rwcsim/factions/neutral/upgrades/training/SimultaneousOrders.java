package rwcsim.factions.neutral.upgrades.training;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Training;

public class SimultaneousOrders implements Cost, Training, OnSkill {
    @Override
    public int price() {
        return 2;
    }

    @Override
    public String getUpgradeName() {
        return "Simultaneous Orders";
    }
}
