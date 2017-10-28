package rwcsim.basicutils.unit;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.EnumSet;
import java.util.List;

public abstract class WaiqarUnit extends BaseUnit {
    public WaiqarUnit() {
        super();
    }

    public EnumSet<UpgradeSlot> availableUpgrades(boolean listContainsArdus, Formation formation) {
        populateUpgrades(listContainsArdus, formation);
        return legalUpgrades;
    }

    public abstract void populateUpgrades(boolean listContainsArdus, Formation formation);

    public DaqanUnit  getAsDaqanUnit() { return null; }
    public LatariUnit getAsLatariUnit() { return null; }
    public UthukUnit getAsUthukUnit() { return null; }
    public WaiqarUnit getAsWaiqarUnit() {
        return this;
    }

}
