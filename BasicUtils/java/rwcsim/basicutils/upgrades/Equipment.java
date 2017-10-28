package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Equipment extends Upgrade {
    default UpgradeSlot getSlot() {
    return UpgradeSlot.Equipment;
}
}
