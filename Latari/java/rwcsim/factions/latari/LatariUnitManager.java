package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.managers.UnitManager;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.List;

public class LatariUnitManager extends UnitManager {
    public static final int ALIANA_OF_SUMMERSONG = 0;
    public static final int DEEPWOOD_ARCHERS = 1;
    public static final int LEONX_RIDERS = 2;
    public static final int AYMHELIN_SCIONS = 3;
    public static final int MAEGAN_CYNDEWIN = 4;
    public static final int DARNATI_WARRIORS = 5;
//    public static final int WRAITHS = 6;

    public LatariUnitManager() {
        init();
    }

    private void init() {
        availableUnitNames = new String[]{
                "Aliana of Summersong",
                "Deepwood Archers",
                "Leonx Riders",
                "Aymhelin Scions",
                "Maegan Cyndewin",
                "Darnati Warriors",
        };
    }

    @Override
    public int getIdFromName(String name) {
        switch (name) {
            case "Aliana of Summersong": return ALIANA_OF_SUMMERSONG;
            case "Deepwood Archers": return DEEPWOOD_ARCHERS;
            case "Leonx Riders": return LEONX_RIDERS;
            case "Aymhelin Scions": return AYMHELIN_SCIONS;
            case "Maegan Cyndewin": return MAEGAN_CYNDEWIN;
            case "Darnati Warriors":return DARNATI_WARRIORS;
        }
        return -100;
    }


    @Override
    public Unit getUnit(int unit) {
        switch (unit) {
            case ALIANA_OF_SUMMERSONG: return new AlianaOfSummersong();
            case DEEPWOOD_ARCHERS: return new DeepwoodArchers();
            case LEONX_RIDERS: return new LeonxRiders();
            case AYMHELIN_SCIONS: return new AymhelinScions();
            case MAEGAN_CYNDEWIN: return new MaeganCyndewin();
            case DARNATI_WARRIORS: return new DeepwoodArchers();
        }
        return null;
    }

    @Override
    public List<Formation> availableFormations(int unit) {
        switch (unit) {
            case ALIANA_OF_SUMMERSONG: return new AlianaOfSummersong().availableFormations();
            case DEEPWOOD_ARCHERS: return new DeepwoodArchers().availableFormations();
            case LEONX_RIDERS: return new LeonxRiders().availableFormations();
            case AYMHELIN_SCIONS: return new AymhelinScions().availableFormations();
            case MAEGAN_CYNDEWIN: return new MaeganCyndewin().availableFormations();
            case DARNATI_WARRIORS: return new DeepwoodArchers().availableFormations();
        }
        return null;
    }

    public int formationCost(int unit, Formation formation) {
        switch(unit) {
            case ALIANA_OF_SUMMERSONG: return 33;
            case DEEPWOOD_ARCHERS:
                switch(formation) {
                    case TWO_BY_ONE: return 17;
                    case TWO_BY_TWO: return 30;
                    case TWO_BY_TWO_2: return 30;
                    case THREE_BY_TWO: return 42;
                }
                break;
            case LEONX_RIDERS:
                switch(formation) {
                    case TWO_BY_ONE: return 18;
                    case ONE_BY_THREE: return 24;
                    case TWO_BY_TWO: return 30;
                    case TWO_BY_THREE: return 40;
                }
                break;
            case AYMHELIN_SCIONS:
                switch (formation) {
                    case ONE: return 14;
                    case TWO_BY_ONE: return 25;
                    case TWO_BY_TWO: return 42;
                    case THREE_BY_TWO: return 62;
                }
            case MAEGAN_CYNDEWIN: return 36;
            case DARNATI_WARRIORS:
                switch(formation) {
                    case TWO_BY_ONE: return 19;
                    case TWO_BY_TWO: return 31;
                    case THREE_BY_TWO: return 43;
                    case THREE_BY_THREE: return 60;
                }
                break;
        }
        return 0;
    }

    public List<UpgradeTypes> availableUpgrades(Unit unit, Formation formation) {
        return unit.getAsLatariUnit().availableUpgrades(formation);
    }



}
