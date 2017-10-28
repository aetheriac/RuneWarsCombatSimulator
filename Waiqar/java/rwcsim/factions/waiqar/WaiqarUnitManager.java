package rwcsim.factions.waiqar;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.managers.UnitManager;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeTypes;

import java.util.List;

public class WaiqarUnitManager extends UnitManager {
    public static final int ARDUS_IX_EREBUS = 0;
    public static final int CARRION_LANCERS = 1;
    public static final int REANIMATE_ARCHERS = 2;
    public static final int REANIMATES = 3;
    public static final int ANKAUR_MARO = 4;
    public static final int DEATH_KNIGHTS = 5;
    public static final int WRAITHS = 6;

    public WaiqarUnitManager() {
        init();
    }

    private void init() {
        availableUnitNames = new String[]{
                "Arus Ix'Erebus",
                "Carrion Lancers",
                "Reanimate Archers",
                "Reanimates",
                "Ankaur Maro",
                "Death Knights",
                "Wraiths"
        };
    }

    @Override
    public int getIdFromName(String name) {
        switch (name) {
            case "Arus Ix'Erebus": return ARDUS_IX_EREBUS;
            case "Carrion Lancers": return CARRION_LANCERS;
            case "Reanimate Archers": return REANIMATE_ARCHERS;
            case "Reanimates": return REANIMATES;
            case "Ankaur Maro": return ANKAUR_MARO;
            case "Death Knights": return DEATH_KNIGHTS;
            case "Wraiths": return WRAITHS;
        }
        return -100;
    }

    @Override
    public Unit getUnit(int unit) {
        switch (unit) {
            case ARDUS_IX_EREBUS: return new ArdusIxErebus();
            case CARRION_LANCERS: return new CarrionLancer();
            case REANIMATE_ARCHERS: return new ReanimateArchers();
            case REANIMATES: return new Reanimates();
            case ANKAUR_MARO: return new AnkaurMaro();
            case DEATH_KNIGHTS: return new DeathKnights();
//            case WRAITHS: return new Wraiths().avaialbleFormations();
        }
        return null;
    }

    @Override
    public List<Formation> availableFormations(int unit) {
        switch (unit) {
            case ARDUS_IX_EREBUS: return new ArdusIxErebus().availableFormations();
            case CARRION_LANCERS: return new CarrionLancer().availableFormations();
            case REANIMATE_ARCHERS: return new ReanimateArchers().availableFormations();
            case REANIMATES: return new Reanimates().availableFormations();
            case ANKAUR_MARO: return new AnkaurMaro().availableFormations();
            case DEATH_KNIGHTS: return new DeathKnights().availableFormations();
//            case WRAITHS: return new Wraiths().avaialbleFormations();
        }
        return null;
    }

    public int formationCost(int unit, Formation formation) {
        switch(unit) {
            case ARDUS_IX_EREBUS: return 37;
            case CARRION_LANCERS:
                switch(formation) {
                    case ONE: return 15;
                    case TWO_BY_ONE: return 27;
                    case TWO_BY_TWO: return 46;
                    case THREE_BY_TWO: return 68;
                }
                break;
            case REANIMATE_ARCHERS:
                switch(formation) {
                    case TWO_BY_ONE: return 18;
                    case TWO_BY_TWO: return 32;
                    case THREE_BY_TWO: return 45;
                }
                break;
            case REANIMATES:
                switch (formation) {
                    case TWO_BY_ONE: return 16;
                    case TWO_BY_TWO: return 26;
                    case THREE_BY_TWO: return 35;
                    case THREE_BY_THREE: return 50;
                    case FOUR_BY_THREE: return 64;
                }
            case ANKAUR_MARO:
                switch(formation) {
                    case ONE: return 40;
                }
                break;
            case DEATH_KNIGHTS:
                switch(formation) {
                    case TWO_BY_ONE: return 24;
                    case TWO_BY_TWO: return 42;
                    case TWO_BY_THREE: return 55;
                }
                break;
            case WRAITHS:
                break;
        }
        return 0;
    }

    public List<UpgradeTypes> availableUpgrades(Unit unit, Formation formation) {
        boolean containsArdus = false;
        for (DeployableUnit du : getUnitList()) {
            if (du.getUnit() instanceof ArdusIxErebus) {
                containsArdus = true;
                break;
            }
        }
        return unit.getAsWaiqarUnit().availableUpgrades(containsArdus, formation);
    }



}
