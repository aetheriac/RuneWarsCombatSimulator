package rwcsim.basicutils.upgrades;


import rwcsim.basicutils.slots.UpgradeSlot;

public interface Artifact {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Artifact;
    }
}
