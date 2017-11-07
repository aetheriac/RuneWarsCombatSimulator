package rwcsim.basicutils.upgrades;


import rwcsim.basicutils.slots.UpgradeSlot;

public interface Training {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Training;
    }
}
