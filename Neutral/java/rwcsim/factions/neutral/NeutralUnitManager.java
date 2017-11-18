package rwcsim.factions.neutral;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.managers.UnitManager;

import java.util.List;

public class NeutralUnitManager extends UnitManager {
    public static final int ANY_UNIT = 0;

    public NeutralUnitManager() { init(); }

    private void init() {
        availableUnitNames = new String[]{
            "Any Unit"
        };
    }

    @Override
    public Unit getUnit(int unit) {
        return new AnyUnit();
    }

    @Override
    public List<Formation> availableFormations(int unit) {
        return new AnyUnit().availableFormations();
    }

    @Override
    public int getIdFromName(String name) {
        return ANY_UNIT;
    }



}
