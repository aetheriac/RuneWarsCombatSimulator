package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Music {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Music;
    }
}
