package rwcsim.basicutils.unit;

import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.dials.CommandCache;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.managers.UnitStateManager;

public class DeployableUnit {
    private Unit unit;
    private Formation formation;

    private int unitPrice;

    private CommandTool commandTool;
    private CommandCache commandCache;

    public UnitFormationManager unitFormationManager;
    public UnitStateManager unitStateManager;

    public DeployableUnit(Unit unit, Formation formation) {
        this.unit = unit;
        this.formation = formation;
        this.commandTool = unit.getCommandTool();
        this.commandCache = unit.getCommandCache();

//        this.unitFormationManager = new UnitFormationManager(this);
        this.unitStateManager = new UnitStateManager();

    }

    public Unit getUnit() { return unit; }

    public Formation getFormation() {
        return formation;
    }

    public UnitStateManager getUnitStateManager() {
        return unitStateManager;
    }

    public void setUnitFormationManager(UnitFormationManager unitFormationManager) { this.unitFormationManager = unitFormationManager; }
    public UnitFormationManager getUnitFormationManager() { return unitFormationManager; }

    public DiePool getDiePool(AttackType type) {
        switch (type) {
            case MELEE_ATTACK:
                return getUnit().getMeleeAttackPool();
            case RANGED_ATTACK:
                return getUnit().getRangedAttackPool();
        }
        return null;
    }


    public boolean canReroll() {
        return (getRerollDieCount()>0 || hasPartialRank());
    }

    public boolean hasPartialRank() {
        return unitFormationManager.hasPartialRank();
    }

    public int getRerollDieCount() {
        int dieRerolls = unitFormationManager.getCurrentRanks()-1;
        return dieRerolls;
    }


}
