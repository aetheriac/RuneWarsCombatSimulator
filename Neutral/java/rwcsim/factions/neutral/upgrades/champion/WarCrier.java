package rwcsim.factions.neutral.upgrades.champion;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Champion;

public class WarCrier implements Cost, Champion, OnSkill {
    @Override
    public int price() {
        return 5;
    }

    @Override
    public String getUpgradeName() {
        return "War Crier";
    }
}
