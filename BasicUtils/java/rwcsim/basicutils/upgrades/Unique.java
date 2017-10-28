package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Unique {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Unique;
    }
}
