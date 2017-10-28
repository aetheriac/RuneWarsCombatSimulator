package rwcsim.factions.neutral.upgrades.equipment;

import rwcsim.basicutils.ActionType;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.ExchangeAction;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.upgrades.Equipment;

public class TemperedSteel implements Cost, Equipment, Exhaustable, ExchangeAction {
    private boolean _exhausted = false;

    @Override
    public int price() {
        return 3;
    }

    @Override
    public boolean isExhausted() {
        return _exhausted;
    }

    @Override
    public void exhaust() {
        _exhausted = true;
    }

    @Override
    public void reset() {
        _exhausted = false;
    }

    @Override
    public ActionType spend(ActionType action) {
        if (action == ActionType.SURGE && !isExhausted()) {
            exhaust();
            return ActionType.HIT;
        }
        return ActionType.BLANK;
    }
}
