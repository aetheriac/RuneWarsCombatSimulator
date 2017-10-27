package rwcsim.basicutils.concepts;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.unit.DaqanUnit;
import rwcsim.basicutils.unit.LatariUnit;
import rwcsim.basicutils.unit.UthukUnit;
import rwcsim.basicutils.unit.WaiqarUnit;
import rwcsim.basicutils.upgrades.UpgradeSlot;
import rwcsim.basicutils.upgrades.UpgradeType;

import java.util.ArrayList;
import java.util.HashMap;
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
    void populateUpgrades(Formation formation);

    CommandTool getCommandTool();
    DiePool getMeleeAttackPool();
    void setMeleeAttackPool(DiePool diePool);
    DiePool getRangedAttackPool();
    void setRangedAttackPool(DiePool diePool);

    void setAbilities();
    void addAbility(Ability ability);
    Map<Integer, Ability<?>> getAbilities();

    void registerUpgrade(Stage stage, UpgradeSlot upgradeSlot);
    Map<Integer, List<UpgradeSlot>> getStageRegister();
}
