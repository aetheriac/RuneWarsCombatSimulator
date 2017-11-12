package rwcsim.factions.neutral.upgrades.champion;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.upgrades.Champion;

public class DispatchRunner implements Champion, Cost, Exhaustable, OnSkill {
    @Override
    public int price() {
        return 7;
    }

    @Override
    public boolean isExhausted() {
        return false;
    }

    @Override
    public void exhaust() {

    }

    @Override
    public void reset() {

    }

    @Override
    public String getUpgradeName() {
        return "Dispatch Runner";
    }
}
