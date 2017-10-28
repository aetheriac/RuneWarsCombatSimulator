package rwcsim.factions.uthuk;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.managers.UnitManager;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.List;

public class UthukUnitManager extends UnitManager {
    public static final int RAVOS_THE_EVERHUNGRY = 0;
    public static final int UTHUK_BERSERKERS = 1;
    public static final int FLESH_RIPPERS = 2;
    public static final int SPINE_THRESHERS = 3;
//    public static final int MAEGAN_CYNDEWIN = 4;
//    public static final int DARNATI_WARRIORS = 5;
//    public static final int WRAITHS = 6;

    public UthukUnitManager() {
        init();
    }

    private void init() {
        availableUnitNames = new String[]{
                "Ravos the Everhungry",
                "Uthuk Berserkers",
                "Flesh Rippers",
                "Spined Threshers",
//                "Ankaur Maro",
//                "Death Knights",
//                "Wraiths"
        };
    }


    @Override
    public int getIdFromName(String name) {
        switch (name) {
            case "Ravos the Everhungry": return RAVOS_THE_EVERHUNGRY;
            case "Uthuk Berserkers": return UTHUK_BERSERKERS;
            case "Flesh Rippers": return FLESH_RIPPERS;
            case "Spined Threshers": return SPINE_THRESHERS;
        }
        return -100;
    }


    @Override
    public Unit getUnit(int unit) {
        switch (unit) {
            case RAVOS_THE_EVERHUNGRY: return new RavosTheEverhungry();
            case UTHUK_BERSERKERS: return new UthukBerserkers();
            case FLESH_RIPPERS: return new FleshRippers();
            case SPINE_THRESHERS: return new SpinedThreshers();
//            case MAEGAN_CYNDEWIN: return new MaeganCyndewin().availableFormations();
//            case DARNATI_WARRIORS: return new DeepwoodArchers().availableFormations();
        }
        return null;
    }

    @Override
    public List<Formation> availableFormations(int unit) {
        switch (unit) {
            case RAVOS_THE_EVERHUNGRY: return new RavosTheEverhungry().availableFormations();
            case UTHUK_BERSERKERS: return new UthukBerserkers().availableFormations();
            case FLESH_RIPPERS: return new FleshRippers().availableFormations();
            case SPINE_THRESHERS: return new SpinedThreshers().availableFormations();
//            case MAEGAN_CYNDEWIN: return new MaeganCyndewin().availableFormations();
//            case DARNATI_WARRIORS: return new DeepwoodArchers().availableFormations();
        }
        return null;
    }

    public int formationCost(int unit, Formation formation) {
        switch(unit) {
            case RAVOS_THE_EVERHUNGRY: return 40;
            case UTHUK_BERSERKERS:
                switch(formation) {
                    case TWO_BY_ONE: return 16;
                    case TWO_BY_TWO: return 27;
                    case THREE_BY_TWO: return 37;
                    case THREE_BY_THREE: return 50;
                }
                break;
            case FLESH_RIPPERS:
                switch(formation) {
                    case TWO_BY_ONE: return 22;
                    case TWO_BY_TWO: return 38;
                    case TWO_BY_THREE: return 63;
                    case THREE_BY_THREE: return 75;
                }
                break;
            case SPINE_THRESHERS:
                switch (formation) {
                    case ONE: return 18;
                    case TWO_BY_ONE: return 28;
                    case TWO_BY_TWO: return 48;
                    case THREE_BY_TWO: return 72;
                }
//            case MAEGAN_CYNDEWIN: return 36;
//            case DARNATI_WARRIORS:
//                switch(formation) {
//                    case TWO_BY_ONE: return 19;
//                    case TWO_BY_TWO: return 31;
//                    case THREE_BY_TWO: return 43;
//                    case THREE_BY_THREE: return 60;
//                }
//                break;
//            case WRAITHS:
//                break;
        }
        return 0;
    }

    public List<UpgradeTypes> availableUpgrades(Unit unit, Formation formation) {
        return unit.getAsUthukUnit().availableUpgrades(formation);
    }



}
