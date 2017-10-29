package rwcsim.gui.controller;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.systems.MassAttackLoopTest;
import rwcsim.basicutils.systems.SimSetup;
import rwcsim.basicutils.systems.SimulationAttackLoop;


public class SimulatorController {
    private SimSetup ss;
    private MassAttackLoopTest malt;

    public SimulatorController() {
        malt = new MassAttackLoopTest();
    }

    public void runSimulation(Unit firstUnit, Formation firstFormation, Unit secondUnit, Formation secondFormation, int simulationCount, SimulationAttackLoop.ProgressCallback callback) {
        ss = new SimSetup(firstUnit, firstFormation, secondUnit, secondFormation);
        MassAttackLoopTest.setSimCount(simulationCount);
        malt.setupLoops(ss, callback);
    }
}