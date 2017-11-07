package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Champion {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Champion;
    }
}
