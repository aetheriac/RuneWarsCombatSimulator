package rwcsim.gui.controller;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.dials.*;
import rwcsim.basicutils.systems.MassAttackLoopTest;
import rwcsim.basicutils.systems.SimSetup;
import rwcsim.basicutils.systems.SimulationAttackLoop;
import rwcsim.interactions.ai.behaviors.RerollBehavior;


public class SimulatorController {
    private SimSetup ss;
    private MassAttackLoopTest malt;

    public SimulatorController() {
        malt = new MassAttackLoopTest();
    }

    public void runSimulation(Unit firstUnit, Formation firstFormation, RerollBehavior firstBehavior, Unit secondUnit, Formation secondFormation, RerollBehavior secondBehavior, int simulationCount, SimulationAttackLoop.ProgressCallback callback) {
        ss = new SimSetup(firstUnit, firstFormation, firstBehavior, secondUnit, secondFormation, secondBehavior);

        setupCommandCaches(ss);

        MassAttackLoopTest.setSimCount(simulationCount);
        malt.setupLoops(ss, callback);
    }

    public void setupCommandCaches(SimSetup ss) {
        // scenario first unit engages with melee attack in first round
        // second unit was prepared and returns melee attack
        // forcing the attack initiatives and such for now

        CommandCache cc1 = new CommandCache();
        CommandCache cc2 = new CommandCache();

        // spearmen vs reanimates
        DialFace com = new DialFace(4, Face.MARCH, FaceColor.BLUE, 2);
        DialFace mod = new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE);
        Command command = new Command(com, mod);
        cc1.setCommand(0, command);

        com = new DialFace(3, Face.ATTACK_MELEE, FaceColor.YELLOW);
        mod = new DialFace(Face.SKILL, FaceColor.WHITE);
        command = new Command(com, mod);
        cc1.setCommand(1, command);


        com = new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED);
        mod = new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 1);
        command = new Command(com, mod);
        cc2.setCommand(0, command);

        ss.getFirst().getUnit().setCommandCache(cc1);
        ss.getSecond().getUnit().setCommandCache(cc2);
    }
}