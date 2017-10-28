package rwcsim.basicutils.upgrades;

import rwcsim.basicutils.slots.UpgradeSlot;

public interface Heraldry {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Heraldry;
    }
}
