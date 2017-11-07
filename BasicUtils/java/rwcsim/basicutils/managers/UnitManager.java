package rwcsim.basicutils.managers;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Manager;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.unit.DeployableUnit;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public abstract class UnitManager implements Manager {
    List<DeployableUnit> unitList = new ArrayList<>();
    public String[] availableUnitNames;
    public abstract Unit getUnit(int unit);

    public String[] getAvailableUnitNames() {
        return availableUnitNames;
    }
    public List<DeployableUnit> getUnitList() {
        return unitList;
    }

    
    public abstract List<Formation> availableFormations(int unit);
    public abstract int getIdFromName(String name);
    public List<Formation> availableFormations(String unit) {
        return availableFormations(getIdFromName(unit));
    }
    public EnumSet<UpgradeSlot> availableUpgrades(Unit unit, Formation formation) {
        return unit.availableSlots(formation);
    }
}
