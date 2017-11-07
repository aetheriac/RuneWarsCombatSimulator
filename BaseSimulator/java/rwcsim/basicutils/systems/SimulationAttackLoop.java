package rwcsim.basicutils.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.managers.UnitStateManager;
import rwcsim.basicutils.ruleset.RerollFromDialog;
import rwcsim.basicutils.runes.RuneManager;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.interactions.DefaultInteractionManager;
import rwcsim.interactions.InteractionManager;
import rwcsim.interactions.ai.behaviors.RerollBehavior;
import rwcsim.test.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationAttackLoop implements Callable<Statistics> {
    private static Logger log = LogManager.getLogger(SimulationAttackLoop.class);

    private static int DEMARKATION = 10000;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    AttackType attackType = AttackType.MELEE_ATTACK;
    InteractionManager firstInteraction;
    InteractionManager secondInteraction;

    DeployableUnit firstUnit;
    DeployableUnit secondUnit;

    UnitFormationManager firstFormation;
    UnitFormationManager secondFormation;

    RerollBehavior firstBehavior;
    RerollBehavior secondBehavior;

    SimSetup setup;
    private ProgressCallback progressCallback;

    List<String> messages = new ArrayList<>();
    int rounds = 0;

    public interface ProgressCallback {
        void progress(int runNumberCompleted);
    }

    public SimulationAttackLoop(SimSetup setup, ProgressCallback callback) {
        this.setup = setup;
        this.progressCallback = callback;

        this.setup.getFirst().getUnit().populateFormations();
        this.setup.getFirst().getUnit().populateSlots(this.setup.getFirst().getFormation());

        this.setup.getSecond().getUnit().populateFormations();
        this.setup.getSecond().getUnit().populateSlots(this.setup.getSecond().getFormation());
    }

    public static void resetCounter() { atomicInteger.set(0); }
//    public SimulationAttackLoop(InteractionManager firstInteraction, DeployableUnit firstUnit, InteractionManager secondInteraction, DeployableUnit secondUnit) {
//        this.firstInteraction = firstInteraction;
//        this.firstUnit = firstUnit;
//        this.firstFormation = new UnitFormationManager(firstUnit);
//
//
//        this.secondInteraction = secondInteraction;
//        this.secondUnit = secondUnit;
//        this.secondFormation = new UnitFormationManager(secondUnit);
//    }

    @Override
    public Statistics call() throws Exception {
        int runNum = atomicInteger.incrementAndGet();
        if (runNum != 0 && runNum % DEMARKATION == 0) {
            log.info("Processing: " + runNum);
        }

        this.firstInteraction = DefaultInteractionManager.instance();
        this.firstUnit = setup.getFirst();
        this.firstFormation = new UnitFormationManager(firstUnit);
        this.firstBehavior = new RerollFromDialog();


        this.secondInteraction = DefaultInteractionManager.instance();
        this.secondUnit = setup.getSecond();
        this.secondFormation = new UnitFormationManager(secondUnit);
        this.secondBehavior = new RerollFromDialog();

        simulateLoop();

        UnitStateManager firstUSM = firstFormation.setUSMForStats(firstUnit.unitStateManager);
        UnitStateManager secondUSM = secondFormation.setUSMForStats(secondUnit.unitStateManager);

        Statistics stats = new Statistics(rounds, firstUSM, secondUSM);

        firstInteraction = null;
        secondInteraction = null;
        firstFormation = null;
        secondFormation = null;
        firstUnit = null;
        secondUnit = null;

//        atomicInteger.set(0);

        if (progressCallback != null ) {
            progressCallback.progress(runNum);
        }

        return stats;
    }

    public void simulateLoop() {
        RuneManager.getInstance().throwRunes();
        AttackLoop attackLoop;

        InteractionManager attackerInteraction;
        InteractionManager defenderInteraction;

        UnitFormationManager attackerFormation;
        UnitFormationManager defenderFormation;

        RerollBehavior attackerBehavior;
        RerollBehavior defenderBehavior;

        while ((firstFormation.isAlive() && secondFormation.isAlive()) && rounds <= 7) {
            messages.add("Round "+ rounds);
            log.debug("Round: "+ rounds);
//            System.out.println("Round: "+rounds);
            if (rounds % 2 == 0) {
                attackerInteraction = firstInteraction;
                attackerFormation = firstFormation;
                attackerBehavior = firstBehavior;
                defenderInteraction = secondInteraction;
                defenderFormation = secondFormation;
                defenderBehavior = secondBehavior;
            } else {
                defenderInteraction = firstInteraction;
                defenderFormation = firstFormation;
                defenderBehavior = firstBehavior;
                attackerInteraction = secondInteraction;
                attackerFormation = secondFormation;
                attackerBehavior = secondBehavior;
            }

            messages.add("First ("+ firstFormation.figuresRemaining()+"): "+ firstFormation.isAlive());
            messages.add("Second ("+ secondFormation.figuresRemaining()+"): "+ secondFormation.isAlive());

            attackLoop = new AttackLoop(attackerInteraction, attackerFormation, defenderInteraction, defenderFormation, attackType, attackerBehavior, rounds);
            attackLoop.processAttack();

            if (defenderFormation.isAlive()) {
                attackLoop = new AttackLoop(defenderInteraction, defenderFormation, attackerInteraction, attackerFormation, attackType, defenderBehavior, rounds);
                attackLoop.processAttack();
            }

            firstFormation.endActivationPhase(rounds);
            secondFormation.endActivationPhase(rounds);

            messages.add("First ("+ firstFormation.figuresRemaining()+"): "+ firstFormation.isAlive());
            messages.add("Second ("+ secondFormation.figuresRemaining()+"): "+ secondFormation.isAlive());

            rounds++;
        }

        String message = firstFormation.isAlive()?"First is Alive":"First is Dead";
        log.debug(message);
        message = secondFormation.isAlive()?"Second is Alive":"Second is Dead";
        log.debug(message);
        log.debug("Seed: "+ RuleSetManager.randomSeed);

        for (String s:messages) {
            log.debug(s);
        }
    }
}
