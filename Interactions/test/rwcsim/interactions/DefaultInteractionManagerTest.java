package rwcsim.interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.dice.*;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.ruleset.RerollFromDialog;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.factions.waiqar.Reanimates;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultInteractionManagerTest {
    DefaultInteractionManager dim;
    RerollBehavior rerollBehavior = new RerollFromDialog();
    AttackType type = AttackType.MELEE_ATTACK;
    static long dimtSeed = 1884333l;

    @BeforeAll
    public static void mainSetup() {
        RuleSetManager.addRule(new RerollFromDialog(), true);
        RuleSetManager.resetFullRandom(dimtSeed);
    }

    @BeforeEach
    public void setup() {
        dim = new DefaultInteractionManager();
    }

    @AfterEach
    public void tearDown() {
        dim = null;
    }

    @Test
    public void rerollFromDialogTest() {
        Unit unit = new Reanimates();
        Formation formation = Formation.THREE_BY_TWO;
        UnitFormationManager attacker = new UnitFormationManager(new DeployableUnit(unit, formation));
        Map< Die, List< DieFace >> checking = new HashMap<>();
        Map< Die, List< DieFace >> results = new HashMap<>();
        List<DieFace> faces = new ArrayList<>();
        faces.add(DieFace.BLANK);
        checking.put(RedDie.get(), faces);


        results = dim.rerollFromDialog(1, true, attacker,  checking, type, rerollBehavior);

        Map<Die, List<DieFace>> expectedResults = new HashMap<>();
        expectedResults.put(RedDie.get(), new ArrayList<>());
        expectedResults.get(RedDie.get()).add(DieFace.HIT_MORALE);
        expectedResults.put(BlueDie.get(), new ArrayList<>());
        expectedResults.put(WhiteDie.get(), new ArrayList<>());

        assertEquals(expectedResults, results);
    }

}
