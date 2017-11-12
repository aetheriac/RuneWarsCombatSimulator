package rwcsim.factions.neutral.upgrades.equipment;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Equipment;

public class FireRune implements Cost, Equipment, OnSkill {
    @Override
    public int price() {
        return 7;
    }

    @Override
    public String getUpgradeName() {
        return "Fire Rune";
    }
}
