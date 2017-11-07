package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Heavy {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Heavy;
    }
}
