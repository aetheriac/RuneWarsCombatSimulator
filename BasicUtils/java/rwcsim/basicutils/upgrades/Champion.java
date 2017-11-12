package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.concepts.Upgrade;
import rwcsim.basicutils.slots.UpgradeSlot;

public interface Champion extends Upgrade {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Champion;
    }
}
