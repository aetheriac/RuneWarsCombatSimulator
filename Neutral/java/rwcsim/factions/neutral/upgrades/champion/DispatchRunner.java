package rwcsim.factions.neutral.upgrades.champion;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.Exhaustable;
import rwcsim.basicutils.upgrade.Champion;

public class DispatchRunner implements Champion, Cost, Exhaustable, OnSkill {
    @Override
    public int price() {
        return 7;
    }
}