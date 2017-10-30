package rwcsim.basicutils.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Configuration;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.ruleset.AutomaticallyRerollBlanks;
import rwcsim.basicutils.ruleset.Regeneration;
import rwcsim.basicutils.ruleset.Reroll;
import rwcsim.basicutils.ruleset.RerollFromDialog;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.factions.neutral.upgrades.equipment.TemperedSteel;
import rwcsim.interactions.InteractionManager;
import rwcsim.test.Analyzer;
import rwcsim.test.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MassAttackLoopTest {
    private static Logger log = LogManager.getLogger(MassAttackLoopTest.class);

    private static List<Statistics> stats = new ArrayList<>();

    private long testSeed = 421390616945059l;

    public static int THREAD_COUNT = 10;

    public static int getSimCount() {
        return SIM_COUNT;
    }

    public static void setSimCount(int simCount) {
        SIM_COUNT = simCount;
    }

    public static int SIM_COUNT = 500000;
    public static AttackType attackType = AttackType.MELEE_ATTACK;

    InteractionManager waiqarInteraction;
    InteractionManager daqanInteraction;

    DeployableUnit waiqarUnit;
    DeployableUnit daqanUnit;

    UnitFormationManager waiqarFormation;
    UnitFormationManager daqanFormation;

    List<String> messages = new ArrayList<>();

    public MassAttackLoopTest() {
        initEngine();
    }

    public static void initEngine() {
        RuleSetManager.addRule(new Reroll(), false);
        RuleSetManager.addRule(new AutomaticallyRerollBlanks(), false);
        RuleSetManager.addRule(new RerollFromDialog(), true);
        RuleSetManager.addRule(new Regeneration(), true);
        RuleSetManager.addRule(new TemperedSteel(), true);
    }


    public void setupLoops(SimSetup ss) {
        setupLoops(ss, null);
    }

    public void setupLoops(SimSetup ss, SimulationAttackLoop.ProgressCallback callback) {
//        RuleSetManager.resetFullRandom(testSeed);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        List<SimulationAttackLoop> loops = new ArrayList<>();
        List<Future<Statistics>> futures = new ArrayList<>();

        SimulationAttackLoop.resetCounter();
        for (int i = 0; i<SIM_COUNT; i++) {
            loops.add(new SimulationAttackLoop(ss, callback));
        }

        List<Statistics> stats = new ArrayList<>();
        try {
            futures = executor.invokeAll(loops);
        } catch (InterruptedException e) {
            log.info("SimulationAttackLoop issues", e);
        }

        executor.shutdown();

        for (Future<Statistics> fs : futures) {
            try {
                stats.add(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        log.info("Size: "+ stats.size());

        Analyzer.analyze(stats);
    }







    public static void main(String[] args) {
        Configuration.getInstance();
        MassAttackLoopTest malt = new MassAttackLoopTest();
        malt.setupLoops(SimSetup.getSetup());
    }
}
