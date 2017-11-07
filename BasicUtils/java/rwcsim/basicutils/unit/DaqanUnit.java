package rwcsim.basicutils.unit;


import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.slots.UpgradeSlot;

import java.util.EnumSet;

public abstract class DaqanUnit extends BaseUnit {
    public DaqanUnit() {
        super();
    }
    public DaqanUnit  getAsDaqanUnit() {
        return this;
    }
    public LatariUnit getAsLatariUnit() { return null; }
    public UthukUnit getAsUthukUnit() { return null; }
    public WaiqarUnit getAsWaiqarUnit() { return null; }
    public EnumSet<UpgradeSlot> availableUpgrades(Unit unit, Formation formation) {
        return unit.getAsDaqanUnit().availableUpgrades(unit, formation);
    }
}
