package rwcsim.basicutils.unit;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.upgrades.UpgradeSlot;
import rwcsim.basicutils.upgrades.UpgradeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseUnit implements Unit {
    public List<Formation> legalFormations=null;// = new ArrayList<>();
    public List<UpgradeType> legalUpgrades=null;// = new ArrayList<>();
    public Map<Integer, Ability<?>> abilities = new HashMap<>();
    public Map<Integer, List<UpgradeSlot>> upgradeRegister = new HashMap<>();

    public static class NullUnit extends BaseUnit {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public void initializeUnit() {

        }

        @Override
        public Tray getTray() {
            return null;
        }

        @Override
        public DaqanUnit getAsDaqanUnit() {
            return null;
        }

        @Override
        public LatariUnit getAsLatariUnit() {
            return null;
        }

        @Override
        public UthukUnit getAsUthukUnit() {
            return null;
        }

        @Override
        public WaiqarUnit getAsWaiqarUnit() {
            return null;
        }

        @Override
        public Figure getFigure() {
            return null;
        }

        @Override
        public void populateFormations() {

        }

        @Override
        public void populateUpgrades(Formation formation) {

        }

        @Override
        public void setAbilities() {

        }

    }

    public CommandTool commandTool;

    DiePool meleePool;
    DiePool rangedPool;

//    int woundCount;

    public BaseUnit() {
        initializeUnit();
        setAbilities();
    }

    @Override
    public Siege getAsSiege() {
        if (this instanceof Siege) return (Siege)this;
        return null;
    }

    @Override
    public Infantry getAsInfantry() {
        if (this instanceof Infantry) return (Infantry)this;
        return null;
    }

    @Override
    public Cavalry getAsCavalry() {
        if (this instanceof Cavalry) return (Cavalry)this;
        return null;
    }

    @Override
    public Hero getAsHero() {
        if (this instanceof Hero) return (Hero)this;
        return null;
    }

    public List<Formation> availableFormations() {
        if (null == legalFormations) {
            legalFormations = new ArrayList<>();
            populateFormations();
        }
        return legalFormations;
    };

    public List<UpgradeType> availableUpgrades(Formation formation) {
        populateUpgrades(formation);
        return legalUpgrades;
    }

    public CommandTool getCommandTool() {
        return this.commandTool;
    }

    public DiePool getMeleeAttackPool() {
        return this.meleePool;
    }
    public void setMeleeAttackPool(DiePool diePool) {
        this.meleePool = diePool;
    }

    public DiePool getRangedAttackPool() {
        return this.rangedPool;
    }
    public void setRangedAttackPool(DiePool diePool) {
        this.rangedPool = diePool;
    }

    public void addAbility(Ability ability) { abilities.put(ability.getKey(), ability); }
    public Map<Integer, Ability<?>> getAbilities() { return abilities; }

    public void registerUpgrade(Stage stage, UpgradeSlot upgradeSlot) {
        if (!upgradeRegister.containsKey(stage.getKey())) {
            upgradeRegister.put(stage.getKey(), new ArrayList<UpgradeSlot>());
        }
        upgradeRegister.get(stage.getKey()).add(upgradeSlot);
    }
    public Map<Integer, List<UpgradeSlot>> getStageRegister() {
        return upgradeRegister;
    }
}
