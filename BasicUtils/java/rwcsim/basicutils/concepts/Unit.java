package rwcsim.basicutils.concepts;

import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.unit.DaqanUnit;
import rwcsim.basicutils.unit.LatariUnit;
import rwcsim.basicutils.unit.UthukUnit;
import rwcsim.basicutils.unit.WaiqarUnit;
import rwcsim.basicutils.slots.UpgradeSlot;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * Created by dsayles on 8/17/17.
 */
public interface Unit {
    String getName();

    void initializeUnit();

    Siege getAsSiege();

    Infantry getAsInfantry();

    Cavalry getAsCavalry();

    Hero getAsHero();

    Tray getTray();

    DaqanUnit getAsDaqanUnit();
    LatariUnit getAsLatariUnit();
    UthukUnit getAsUthukUnit();
    WaiqarUnit getAsWaiqarUnit();

    Figure getFigure();

    void populateFormations();
    EnumSet<UpgradeSlot> availableSlots(Formation formation);
    void populateSlots(Formation formation);
    List<Upgrade> getUpgrades(UpgradeSlot slot);
    boolean hasUpgrades();

    CommandTool getCommandTool();

    default DiePool getAttackPool(AttackType type) {
        switch (type) {
            case MELEE_ATTACK: return getMeleeAttackPool();
            case RANGED_ATTACK: return getRangedAttackPool();
        }
        return null;
    }

    DiePool getMeleeAttackPool();
    void setMeleeAttackPool(DiePool diePool);
    DiePool getRangedAttackPool();
    void setRangedAttackPool(DiePool diePool);

    void setAbilities();
    void addAbility(Ability ability);
    Map<Integer, Ability<?>> getAbilities();

    void registerUpgrade( UpgradeSlot upgradeSlot, Upgrade upgrade);
    Map<UpgradeSlot, List<Upgrade>> getUpgrades();
//    Map<Integer, List<UpgradeSlot>> getStageRegister();
}
