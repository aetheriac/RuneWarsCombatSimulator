package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Equipment {
    default UpgradeSlot getSlot() {
    return UpgradeSlot.Equipment;
}
}
