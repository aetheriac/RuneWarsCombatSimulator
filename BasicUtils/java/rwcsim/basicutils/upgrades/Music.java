package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.concepts.Upgrade;
import rwcsim.basicutils.slots.UpgradeSlot;

public interface Music extends Upgrade {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Music;
    }
}
