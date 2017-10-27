package rwcsim.factions.neutral.upgrades.equipment;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.upgrades.Equipment;

public class TemperedSteel implements Cost, Equipment, Exhaustable {
    @Override
    public int price() {
        return 3;
    }
}
