package test;

import junit.framework.TestCase;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Configuration;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.systems.AttackLoop;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.factions.daqan.Spearmen;
import rwcsim.factions.waiqar.Reanimates;
import rwcsim.interactions.DefaultInteractionManager;
import rwcsim.interactions.InteractionManager;

public class TestAttackLoop extends TestCase {

    AttackType attackType;

    InteractionManager waiqarInteraction;
    InteractionManager daqanInteraction;

    DeployableUnit waiqarUnit;
    DeployableUnit daqanUnit;

    UnitFormationManager waiqarFormation;
    UnitFormationManager daqanFormation;


    public void setup() {
//        Configuration.getInstance().reloadProperties();
        System.out.println(Configuration.getInstance().get("test.message"));
        attackType = AttackType.MELEE_ATTACK;

        waiqarInteraction = DefaultInteractionManager.instance();
        daqanInteraction = DefaultInteractionManager.instance();

        waiqarUnit = new DeployableUnit(new Reanimates(), Formation.THREE_BY_TWO);
        daqanUnit = new DeployableUnit(new Spearmen(), Formation.THREE_BY_TWO);

        waiqarFormation = new UnitFormationManager(waiqarUnit);
        daqanFormation = new UnitFormationManager(daqanUnit);
    }

    public void testAttackLoop() {
        setup();

        AttackLoop attackLoop;

        InteractionManager attackerInteraction;
        InteractionManager defenderInteraction;

        UnitFormationManager attackerFormation;
        UnitFormationManager defenderFormation;

        for (int i = 0; i < 8; i++) {
            if (i%2==0) {
                attackerInteraction = waiqarInteraction;
                attackerFormation = waiqarFormation;
                defenderInteraction = daqanInteraction;
                defenderFormation = daqanFormation;
            } else {
                defenderInteraction = waiqarInteraction;
                defenderFormation = waiqarFormation;
                attackerInteraction = daqanInteraction;
                attackerFormation = daqanFormation;
            }

            attackLoop = new AttackLoop(attackerInteraction, attackerFormation, defenderInteraction, defenderFormation, attackType);
            attackLoop.processAttack();
        }
    }


}
