package rwcsim.factions.neutral.upgrades.heraldry;

import rwcsim.basicutils.abilities.Impact;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.upgrades.Heraldry;

public class BullPennon implements Cost, Heraldry, AddAbility<Impact> {
     @Override
    public int price() {
        return 3;
    }

    @Override
    public Impact getAbility() {
        return new Impact(1);
    }

    @Override
    public String getUpgradeName() {
        return "Bull Pennon";
    }
}
