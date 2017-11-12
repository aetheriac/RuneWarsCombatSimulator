package rwcsim.basicutils.upgrades;


import rwcsim.basicutils.concepts.Upgrade;
import rwcsim.basicutils.slots.UpgradeSlot;

public interface Artifact extends Upgrade {
    default UpgradeSlot getSlot() {
        return UpgradeSlot.Artifact;
    }
}
