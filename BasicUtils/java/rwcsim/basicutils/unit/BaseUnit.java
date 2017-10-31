package rwcsim.basicutils.unit;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.upgrades.Upgrade;

import java.util.*;

public abstract class BaseUnit implements Unit {
    public List<Formation> legalFormations = new ArrayList<>();
    public EnumSet<UpgradeSlot> legalUpgrades=EnumSet.noneOf(UpgradeSlot.class);
    public Map<Integer, Ability<?>> abilities = new HashMap<>();
//    public Map<Integer, List<UpgradeSlot>> upgradeRegister = new HashMap<>();
//    public EnumSet<UpgradeSlot> allowedUpgrades = EnumSet.noneOf(UpgradeSlot.class);
    public Map<UpgradeSlot, List<Upgrade>> upgradeRegistry = new HashMap<>();

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
        public void populateSlots(Formation formation) {

        }

        @Override
        public boolean hasUpgrades() {
            return false;
        }

        @Override
        public void setAbilities() {

        }

        @Override
        public void registerUpgrade(UpgradeSlot upgradeSlot, Upgrade upgrade) {

        }

        @Override
        public Map<UpgradeSlot, List<Upgrade>> getUpgrades() {
            return null;
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
        if (legalFormations.isEmpty()) {
//            legalFormations = new ArrayList<>();
            populateFormations();
        }
        return legalFormations;
    };

    public EnumSet<UpgradeSlot> availableSlots(Formation formation) {
        populateSlots(formation);
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

    public void registerUpgrade(UpgradeSlot slot, Upgrade upgrade) {
        if (legalUpgrades.contains(slot)) {
            if (!upgradeRegistry.keySet().contains(slot)) {
                upgradeRegistry.put(slot, new ArrayList<>());
            }
            upgradeRegistry.get(slot).add(upgrade);
        }
    }

    @Override
    public List<Upgrade> getUpgrades(UpgradeSlot slot) {
        return upgradeRegistry.get(slot);
    }

    @Override
    public Map<UpgradeSlot, List<Upgrade>> getUpgrades() {
        return upgradeRegistry;
    }

    @Override
    public boolean hasUpgrades() {
        if (upgradeRegistry.size()>0) {
            for (Map.Entry<UpgradeSlot, List<Upgrade>> entry : upgradeRegistry.entrySet() ) {
                if (entry.getValue().size()>0) {
                    return true;
                }
            }
        }
        return false;
    }


//    public void registerUpgrade(UpgradeSlot upgradeSlot, Upgrade upgrade) {
//        if (!upgradeRegistry.containsKey(upgradeSlot)) {
//            upgradeRegistry.put(upgradeSlot, new ArrayList<Upgrade>());
//        }
//        upgradeRegistry.get(upgradeSlot).add(upgrade);
//    }
//    public Map<Integer, List<UpgradeSlot>> getStageRegister() {
//        return upgradeRegister;
//    }
}
