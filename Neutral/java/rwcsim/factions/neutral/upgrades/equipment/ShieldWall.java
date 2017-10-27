package rwcsim.factions.neutral.upgrades.equipment;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.WhileDefending;
import rwcsim.basicutils.upgrades.Equipment;

public class ShieldWall implements Cost, Equipment {
    @Override
    public int price() {
        return 5;
    }
}
